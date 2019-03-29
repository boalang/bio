import java.sql.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.Path;

//import com.mysql.jdbc.Driver;

public class BoaCompilePoller {
	final static String url = "jdbc:mysql://localhost:3306/drupal";
	final static String user = "drupal";
	final static String password = "drupal";
	final static int POLL_TIME = 2000;
	final static int MAX_CONCURRENT_JOBS = 1;

	public static void main(final String[] args) {
		while (true) {
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			ExecutorService execSvc = null;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				execSvc = Executors.newCachedThreadPool();
				// execSvc = Executors.newFixedThreadPool(4);
				con = DriverManager.getConnection(url, user, password);

				while (true) {
					//
					// find jobs that need stopped
					//
					st = con.createStatement();
					rs = st.executeQuery("SELECT id, hadoop_id" + " FROM boa_jobs" + " WHERE hadoop_status=3");

					// spawn thread to process each result
					while (rs.next()) {
						System.out.println("found stop result");
						execSvc.execute(new JobStopper(rs.getInt("id"), rs.getString("hadoop_id")));
					}

					try {
						if (rs != null)
							rs.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
					rs = null;
					try {
						if (st != null)
							st.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
					st = null;

					//
					// find new jobs
					//
					st = con.createStatement();
					// find 1 job per user waiting for compilation
					rs = st.executeQuery("SELECT boa_jobs.id, uid, source, path, compiler_path, run_opts, hadoop_status"
							+ " FROM boa_jobs LEFT JOIN boa_input ON input=boa_input.id" + " WHERE compiler_status=0" +
							// " GROUP BY uid" +
							" ORDER BY submitted ASC");

					// spawn thread to process each result
					while (rs.next()) {
						System.out.println("found run result");
						execSvc.execute(new JobRunner(rs.getInt("id"), rs.getInt("uid"), rs.getInt("hadoop_status"),
								rs.getString("source"), rs.getString("path"), rs.getString("run_opts"),
								rs.getString("compiler_path")));
					}

					try {
						if (rs != null)
							rs.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
					rs = null;
					try {
						if (st != null)
							st.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
					st = null;

					Thread.sleep(POLL_TIME);
				}
			} catch (final Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				rs = null;
				try {
					if (st != null)
						st.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				st = null;
				try {
					if (con != null)
						con.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				con = null;
				try {
					if (execSvc != null)
						execSvc.shutdown();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				execSvc = null;
			}

			// if a connection fails for any reason, sleep a bit longer and retry
			System.out.println("SQL connect reset");
			try {
				Thread.sleep(2 * POLL_TIME);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	static class JobStopper implements Runnable {
		final Connection con;
		final int jobId;
		final String hadoopId;

		public JobStopper(final int jobId, final String hadoopId) throws SQLException, ClassNotFoundException {

			System.out.println("line 149");
			// FIXME: added by Hamid
			// Class.forName("com.mysql.jdbc.Driver");
			System.out.println("line 152");

			con = DriverManager.getConnection(url, user, password);
			this.jobId = jobId;
			this.hadoopId = hadoopId;
		}

		public void run() {
			try {
				System.out.println("processing job " + jobId);

				Statement st = null;
				try {
					// mark the job as stopped
					st = con.createStatement();
					st.executeUpdate(
							"UPDATE boa_jobs SET compiler_status=-2, hadoop_status=-2, hadoop_end=CURRENT_TIMESTAMP(), hadoop_output=CONCAT(hadoop_output, '\nJob was killed by user.\n\n') WHERE id="
									+ jobId);
					Thread.yield();

					stopJob();
				} finally {
					try {
						if (st != null)
							st.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
					st = null;
				}
			} catch (final Exception e) {
				System.out.println("Exception processing job " + jobId);
				e.printStackTrace();
			}
		}

		void stopJob() throws SQLException, IOException, InterruptedException {
			System.out.println("stopping job " + jobId);
			System.out.println("hadoop id: " + hadoopId);

			// submit the job to hadoop
			final Process proc = Runtime.getRuntime()
					.exec(new String[] { "/usr/local/hadoop-1.2.1/bin/hadoop", "job", "-kill", hadoopId });

			// capture the output from the hadoop job process
			final BufferedReader errStream = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String output = "";

			String line = null;
			while ((line = errStream.readLine()) != null)
				output += line + "\n";

			errStream.close();
			proc.waitFor();

			System.out.println("killed job " + jobId);
		}
	}

	static class JobRunner implements Runnable {
		final Connection con;
		final int jobId;
		final int jobUser;
		final int hadoop_status;
		final String source;
		final String input;
		final String inputOpts;
		final String compile_path;

		public JobRunner(final int jobId, final int jobUser, final int hadoop_status, final String source,
				final String input, final String inputOpts, final String compile_path)
				throws SQLException, ClassNotFoundException {

			System.out.println("line 225");
			// FIXME: added by Hamid
			// Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("line 229");
			this.jobId = jobId;
			this.jobUser = jobUser;
			this.hadoop_status = hadoop_status;
			this.source = source;
			this.input = input;
			this.inputOpts = inputOpts == null ? "" : inputOpts;
			this.compile_path = compile_path;
		}

		public void run() {
			try {
				System.out.println("processing job " + jobId);

				Statement st = null;
				try {
					// mark the job as starting to compile
					st = con.createStatement();
					st.executeUpdate(
							"UPDATE boa_jobs SET compile_start=CURRENT_TIMESTAMP(), compiler_status=1, hadoop_status=0, hadoop_id='', hadoop_output='' WHERE id="
									+ jobId);
					Thread.yield();

					// first see if we can cache the job from a prior run
					if (hadoop_status != 4 && cacheProgram())
						return;

					// attempt to compile the job
					File tmp = null;
					try {
						tmp = File.createTempFile("BoaWeb", ".boa", new File("/tmp/"));
						final PrintWriter tmpOut = new PrintWriter(tmp);
						tmpOut.print(source);
						tmpOut.close();
						Thread.yield();

						if (compileProgram(tmp)) {
							// if it compiled, feed the jar to hadoop to run
							File tmpJar = null;
							String cmd = "";
							String jarFilePath = null;
							try {

								// Find the jar file path
								cmd = String.format("find /tmp/ -name %s ",
										tmp.getName().substring(0, tmp.getName().lastIndexOf(".")) + ".jar");

								PrintWriter writer = new PrintWriter("jarAdr.txt", "UTF-8");
								writer.println(cmd);

								jarFilePath = runCmd(cmd);

								writer.println(jarFilePath);
								writer.close();

								// tmpJar = new File(tmp.getCanonicalPath().substring(0,
								// tmp.getCanonicalPath().length() - 3) + "jar");
								tmpJar = new File(jarFilePath);
								Thread.yield();

								runHadoop(tmpJar);
							} finally {
								if (tmpJar != null)
									tmpJar.delete();
							}
						}
					} finally {
						if (tmp != null)
							tmp.delete();
					}
				} finally {
					try {
						if (st != null)
							st.close();
					} catch (final Exception e) {
						System.out.println("#### Line 295");
						e.printStackTrace();
					}
					st = null;
				}
			} catch (final Exception e) {
				System.out.println("Exception processing job " + jobId);
				e.printStackTrace();
			}
		}

		public static String runCmd(String cmd) {
			Runtime rt = Runtime.getRuntime();
			Process proc = null;

			String output = "";
			try {
				proc = rt.exec(cmd, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			try {
				while ((s = stdInput.readLine()) != null) {
					System.out.println(s);
					output += s;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			try {
				while ((s = stdError.readLine()) != null) {
					System.out.println(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String path = output.substring(1);
			return (output);
		}// End of runcmd

		boolean cacheProgram() {
			try {
				Statement st = null;
				try {
					st = con.createStatement();
					final ResultSet rs = st.executeQuery(
							"SELECT old.id FROM boa_jobs new, boa_jobs old WHERE new.id != old.id AND new.input > 1 AND new.input = old.input AND new.source = old.source AND old.hadoop_status = 2 AND old.compiler_status = 2 AND new.id = "
									+ jobId
									+ " ORDER BY UNIX_TIMESTAMP(old.hadoop_end) - UNIX_TIMESTAMP(old.submitted), old.submitted DESC LIMIT 10");
					Thread.yield();

					while (rs.next()) {
						int oldId = rs.getInt("id");

						try {
							Statement st2 = null;
							try {
								st2 = con.createStatement();
								st2.executeUpdate("UPDATE boa_jobs new, (SELECT * FROM boa_jobs WHERE id = " + oldId
										+ ") old " + "SET new.compiler_status = old.compiler_status, "
										+ "new.compiler_output = old.compiler_output, "
										+ "new.compile_start = FROM_UNIXTIME(UNIX_TIMESTAMP(old.compile_start) + UNIX_TIMESTAMP(new.submitted) - UNIX_TIMESTAMP(old.submitted)), "
										+ "new.compile_end = FROM_UNIXTIME(UNIX_TIMESTAMP(old.compile_end) + UNIX_TIMESTAMP(new.submitted) - UNIX_TIMESTAMP(old.submitted)), "
										+ "new.hadoop_start = FROM_UNIXTIME(UNIX_TIMESTAMP(old.hadoop_start) + UNIX_TIMESTAMP(new.submitted) - UNIX_TIMESTAMP(old.submitted)), "
										+ "new.hadoop_end = FROM_UNIXTIME(UNIX_TIMESTAMP(old.hadoop_end) + UNIX_TIMESTAMP(new.submitted) - UNIX_TIMESTAMP(old.submitted)), "
										+ "new.hadoop_status = old.hadoop_status, "
										+ "new.hadoop_output = old.hadoop_output, " + "new.hadoop_id = old.hadoop_id "
										+ "WHERE new.source = old.source AND new.id = " + jobId);

								try {
									st2.executeUpdate("INSERT INTO boa_output (id, length) VALUES (" + jobId + ", 0)");
								} catch (final Exception e) {
								}

								st2.executeUpdate("UPDATE boa_output new, (SELECT * FROM boa_output WHERE id = " + oldId
										+ ") old " + "SET new.length = old.length, "
										+ "new.web_result = old.web_result, " + "new.hash = old.hash "
										+ "WHERE new.id = " + jobId);

								final Path oldPath = new Path("/boa", new Path("" + oldId, new Path("output.txt")));
								final Path newPath = new Path("/boa", new Path("" + jobId, new Path("output.txt")));

								FileSystem fileSystem = null;
								FSDataInputStream in = null;
								FSDataOutputStream out = null;

								try {
									fileSystem = oldPath.getFileSystem(new Configuration());

									if (fileSystem.exists(oldPath) && !fileSystem.exists(newPath)) {
										fileSystem.mkdirs(new Path("/boa", new Path("" + jobId)));

										out = fileSystem.create(newPath);
										in = fileSystem.open(oldPath);

										final byte[] b = new byte[64 * 1024 * 1024];
										int length = 0;

										int numBytes = 0;
										while ((numBytes = in.read(b)) > 0)
											out.write(b, 0, numBytes);

										Thread.yield();

										System.out.println("caching job " + jobId + " from job " + oldId);
										return true;
									}
								} catch (final Exception e) {
									e.printStackTrace();
								} finally {
									try {
										if (in != null)
											in.close();
									} catch (final Exception e) {
										e.printStackTrace();
									}
									try {
										if (out != null)
											out.close();
									} catch (final Exception e) {
										e.printStackTrace();
									}
									try {
										if (fileSystem != null)
											fileSystem.close();
									} catch (final Exception e) {
										e.printStackTrace();
									}
								}

								// if we couldnt cache (most likely the output file is missing), revert so it
								// runs as new
								System.out.println("failed caching job " + jobId + " from job " + oldId);

								st2.executeUpdate(
										"UPDATE boa_output SET length = 0, web_result = NULL, hash = NULL WHERE id = "
												+ jobId);
								st2.executeUpdate(
										"UPDATE boa_jobs " + "SET compiler_status = 1, " + "compiler_output = NULL, "
												+ "compile_start = CURRENT_TIMESTAMP(), " + "compile_end = 0, "
												+ "hadoop_start = 0, " + "hadoop_end = 0, " + "hadoop_status = 0, "
												+ "hadoop_output = '', " + "hadoop_id = '' " + "WHERE id = " + jobId);
							} finally {
								try {
									if (st2 != null)
										st2.close();
								} catch (final Exception e) {
									e.printStackTrace();
								}
								st2 = null;
							}
						} catch (final Exception e) {
							System.out.println("Exception processing job " + jobId);
							e.printStackTrace();
						}
					}

					try {
						rs.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
				} finally {
					try {
						if (st != null)
							st.close();
					} catch (final Exception e) {
						e.printStackTrace();
					}
					st = null;
				}
			} catch (final Exception e) {
				System.out.println("Exception processing job " + jobId);
				e.printStackTrace();
			}

			return false;
		}

		boolean compileProgram(final File input) throws SQLException, IOException, InterruptedException {
			System.out.println("compiling job " + jobId);

			String output = "Compiler path not set for this input! No compile was attempted.";
			boolean error = true;

			if (compile_path != null) {
				// run the compiler
				final Process proc = Runtime.getRuntime().exec(
						new String[] { "/home/hadoop/bin/boa-compile.sh", input.getCanonicalPath(), compile_path },
						null, input.getParentFile());

				System.out.println(input.getCanonicalPath());
				System.out.println(input.getParentFile());
				System.out.println(compile_path);

				// capture the output from the compile process
				final BufferedReader errStream = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

				output = "";
				String line = null;
				while ((line = errStream.readLine()) != null)
					output += line + "\n";

				errStream.close();

				proc.waitFor();
				error = proc.exitValue() != 0;
			}

			// add the output (error or nothing) and set the status
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(
						"UPDATE boa_jobs SET compile_end=CURRENT_TIMESTAMP(), compiler_output=?, compiler_status=? WHERE id="
								+ jobId);
				ps.setString(1, output);
				ps.setInt(2, error ? -1 : 2);
				ps.executeUpdate();
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				ps = null;
			}

			System.out.println("finished compiling job " + jobId + ": " + (error ? "error" : "ok"));
			return !error;
		}

		void runHadoop(final File jar) throws SQLException, IOException, InterruptedException {
			int running = 0;
			while ((running = getRunningJobs()) >= MAX_CONCURRENT_JOBS) {
				System.out.println("sleeping job " + jobId + " (" + running + " running)");
				Thread.sleep(POLL_TIME);
			}

			Statement st = null;
			ResultSet rs = null;
			try {
				// verify the job wasnt stopped
				st = con.createStatement();
				rs = st.executeQuery("SELECT id FROM boa_jobs WHERE id=" + jobId + " AND hadoop_status=0");

				if (!rs.next())
					return;
			} catch (final Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				rs = null;
				try {
					if (st != null)
						st.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				st = null;
			}

			// FIXME technically throttled users hit a race condition and could run more
			// than MAX_CONCURRENT_JOBS
			// this attempts to limit how often that happens by immediately marking this job
			// as running
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(
						"UPDATE boa_jobs SET hadoop_status=1, hadoop_start=CURRENT_TIMESTAMP() WHERE id=" + jobId);
				ps.executeUpdate();
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				ps = null;
			}

			System.out.println("running job " + jobId);
			System.out.println("jar: " + jar.getCanonicalPath());
			System.out.println("input: " + input);
			System.out.println("out: " + jar.getPath());
			System.out.println("options: " + inputOpts);
			System.out.println("jar name: " + jar.getName());

			// submit the job to hadoop
			final Process proc = Runtime.getRuntime().exec(new String[] { "/home/hadoop/bin/boa-run.sh", jar.getPath(),
					input, jar.getPath(), "-j", "" + jobId, inputOpts }); // FIXME: jar.getpath()-->jar name
			// path to remove the jar
			// jar.getName().substring(0,jar.getName().lastIndexOf("."))

			// capture the output from the hadoop job submission process
			final BufferedReader errStream = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String output = "";

			String line = null;
			while ((line = errStream.readLine()) != null)
				output += line + "\n";

			errStream.close();

			proc.waitFor();
			final boolean error = proc.exitValue() != 0;

			// add the output (error or nothing) and set the status
			try {
				ps = con.prepareStatement(
						"UPDATE boa_jobs SET hadoop_start=CURRENT_TIMESTAMP(), hadoop_output=?, hadoop_status=2 WHERE id="
								+ jobId);
				ps.setString(1, output);
				// ps.setInt(2, error ? -1 : 1);
				ps.executeUpdate();

				System.out.println("Update Boa jobs table once it done! ");
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				ps = null;
			}

			System.out.println("finished running job " + jobId);
		}

		int getRunningJobs() {

			System.out.println("------------------------------------------getRunningJobs()");
			Statement st = null;
			ResultSet rs = null;

			try {
				st = con.createStatement();
				// this will always return 0 for non-throttled (role ID 7) users
				rs = st.executeQuery(
						"SELECT COUNT(id) AS running FROM boa_jobs, users_roles WHERE hadoop_status=1 AND boa_jobs.uid="
								+ jobUser + " AND boa_jobs.uid=users_roles.uid AND users_roles.rid=7");

				System.out.println(
						"SELECT COUNT(id) AS running FROM boa_jobs, users_roles WHERE hadoop_status=1 AND boa_jobs.uid="
								+ jobUser + " AND boa_jobs.uid=users_roles.uid AND users_roles.rid=7");

				if (rs.next()) {
					// FXIME: remove
					System.out.println("if (rs.next()) {");

					return rs.getInt("running");
				}
			} catch (final Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				rs = null;
				try {
					if (st != null)
						st.close();
				} catch (final Exception e) {
					e.printStackTrace();
				}
				st = null;
			}

			return 0;
		}
	}

}
