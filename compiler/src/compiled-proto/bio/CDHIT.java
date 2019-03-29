package bio;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CDHIT {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		// parse("/home/Boa4Genomics/bioExamples/testoutput");

		parse_annotation("/Users/hbagheri/Downloads/nr_subset/nr95_5M_annotations");

	}

	private static void parse_annotation(String strfile) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file = new File(strfile);

		Scanner sc = new Scanner(file);

		String orgName = null;
		String proteinFunc = null;
		String keyId = null;

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			System.out.println(line);

			String[] ann = line.split("^A");
			for (int i=0;  i< ann.length ; i++) {
				String[] words = ann[i].split(" ");
				keyId = words[0].substring(words[0].indexOf(">") + 1);
				if (ann[i].indexOf("[") > 0) {
					proteinFunc = ann[i].substring(ann[i].indexOf(" ")+1, ann[i].indexOf("["));
					orgName = ann[i].substring(ann[i].indexOf("[") + 1, ann[i].indexOf("]"));
				}else {
					proteinFunc= ann[i].substring(ann[i].indexOf(" ") +1, ann[i].length());
				}

				System.out.println(keyId);
				System.out.println(proteinFunc);
				System.out.println(orgName);
				System.out.println(" ---");
			}
			



		}
	}

	public static String runCmd2(String cmd) {
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

	public static String runCmd(String cmd) {
		Runtime rt = Runtime.getRuntime();
		Process proc = null;

		String output = "";
		try {
			// proc = rt.exec(cmd, null);
			// "sed -n " + "\"" + "/>" + rep_id + "/,/>/p" +"\" " +
			// "/home/hamid58b/Downloads/cd-hit-v4.6.8-2017-1208/env_nr";
			// String[] cmd1=new String[] {"sed", "-n", "/", cmd,
			// "/,/>/p","/home/hamid58b/Downloads/cd-hit-v4.6.8-2017-1208/env_nr"};
			// System.out.println("###");
			// System.out.println(cmd1);

			String[] cmd2 = { "/bin/sh", "/home/hamid58b/eclipse-workspace/test/src/test/testrun.sh" };
			proc = rt.exec(cmd2);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		// read the output from the command
		// System.out.println("Here is the standard output of the command:\n");
		String s = null;
		try {
			while ((s = stdInput.readLine()) != null) {
				// System.out.println(s);
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

		return (output);
	}// End of runcmd

	public static void parse(String fname) throws FileNotFoundException {

		File file = new File(fname);
		Scanner sc = new Scanner(file);
		String line1 = "", line2 = "";
		int clusterID = -1;

		while (sc.hasNextLine()) {
			line1 = sc.nextLine();

			if (line1.startsWith(">")) {
				// new cluster clstID=?
				clusterID = Integer.parseInt(line1.substring(line1.indexOf(" ") + 1));

				line2 = sc.nextLine();

				if (!line2.startsWith(">")) {
					// same cluster
					System.out.println("line1: " + line1);
					System.out.println("cluster# " + clusterID + " " + line2);

					String[] rep = line2.split("\\s+");
					int cl_length = Integer.parseInt(rep[1].substring(0, rep[1].indexOf("aa")));
					String rep_id = rep[2].substring(1, rep[2].indexOf("..."));
					// representative sample
					if (line2.endsWith("*")) {
						System.out.println("Representative of the cluster");

						System.out.println(rep_id);
						System.out.println(cl_length);
					} else {
						String[] pos = rep[4].split(":");

						String[] sim = rep[4].split(":")[3].split("/");
						System.out.println(pos[0] + " " + pos[1]);

						System.out.println("seq id: " + rep_id);

					}

				} else {
					// next cluster
				}

			} else {
				// same cluster:
				System.out.println("cluster# " + clusterID + " " + line1);

				String[] rep = line1.split("\\s+");
				int cl_length = Integer.parseInt(rep[1].substring(0, rep[1].indexOf("aa")));
				String rep_id = rep[2].substring(1, rep[2].indexOf("..."));

				if (line1.endsWith("*")) { // representative sequence
					System.out.println("Representative of the cluster");

					System.out.println(rep_id);
					System.out.println(cl_length);
				} else { // sequence
					System.out.println("seq id: " + rep_id);

					String[] pos = rep[4].split(":");
					int start = Integer.parseInt(pos[0]);
					int end = Integer.parseInt(pos[1]);
					int rep_start = Integer.parseInt(pos[2]);
					int rep_end = Integer.parseInt(pos[3].split("/")[0]);
					int similarity = Integer.parseInt(pos[3].split("/")[1].substring(0, 2));
					String[] sim = rep[4].split(":")[3].split("/");
					System.out.println(pos[0] + " " + pos[1]);

					// retreive the sequence from env_nr
					String cmd = "sed -n " + "\"" + "/>" + rep_id + "/,/>/p" + "\" "
							+ "/home/hamid58b/Downloads/cd-hit-v4.6.8-2017-1208/env_nr";

					cmd = "less env_nr_annotation2 | grep \"VAX37673.1\"";
					String reads = runCmd(rep_id);
					String defLine = reads.substring(reads.indexOf(":") + 1);
					// System.out.println(cmd);
					System.out.println(defLine);
					String[] annotations = reads.split("");
					System.out.println(annotations);

				}

			}

		}

	}

}

