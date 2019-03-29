package boa.dsi.storage.sequencefile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.tools.ant.util.SymbolicLinkUtils;
import org.biojava.nbio.genome.parsers.gff.FeatureI;
import org.biojava.nbio.genome.parsers.gff.FeatureList;
import org.biojava.nbio.genome.parsers.gff.GFF3Reader;

import com.aol.cyclops.data.async.Queue;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sun.tools.javac.util.StringUtils;

import boa.dsi.DSIProperties;
import boa.dsi.dsource.AbstractSource;
import boa.dsi.storage.AbstractStorage;
import boa.types.GFeature.Assembler;
import boa.types.GFeature.AssemblerRoot;
import boa.types.GFeature.Attribute;
import boa.types.GFeature.Feature;
import boa.types.GFeature.FeatureRoot;
import boa.types.GFeature.Genome;
import boa.types.GFeature.Sequence;
//import boa.types.Nr.Annotation;
//import boa.types.Nr.Cluster;
import boa.types.Nr2.*;

public class SequenceFileStorage extends AbstractStorage {
	protected Configuration conf;
	SequenceFile.Reader seqFileReader;
	protected SequenceFile.Writer project_seqFileWriter;
	protected SequenceFile.Writer assembler_seqFileWriter;
	protected SequenceFile.Writer nr_seqFileWriter;
	protected SequenceFile.Writer annotations_seqFileWriter;
	protected SequenceFile.Writer feature_seqFileWriter;

	static long total_length, total_gap_length;
	static long contig_N50, contig_count = 0;
	static long scaffold_N50, scaffold_count = 0;
	static String refseqID = null, taxID;
	static long assembly_date=0;

	public SequenceFileStorage(String location, AbstractSource parser) {
		super(location, parser);
		conf = new Configuration();
	}

	public SequenceFileStorage(AbstractSource parser) {
		// dataset location
		super(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/" + DSIProperties.HADOOP_SEQ_FILE_NAME, parser);
		conf = new Configuration();
	}

	@Override
	public boolean isAvailable(String source) {
		org.apache.hadoop.io.Text key = (org.apache.hadoop.io.Text) ReflectionUtils
				.newInstance(seqFileReader.getKeyClass(), conf);
		org.apache.hadoop.io.BytesWritable keyValue = (org.apache.hadoop.io.BytesWritable) ReflectionUtils
				.newInstance(seqFileReader.getValueClass(), conf);
		try {
			this.seqFileReader.next(key, keyValue);
			AbstractStorage.LOG.info(key.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void store(List<GeneratedMessage> dataInstance) {
		this.openWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/" + DSIProperties.HADOOP_SEQ_FILE_NAME);
		for (GeneratedMessage data : dataInstance) {
			try {

				String key = Genome.parseFrom(data.toByteArray()).getRefseq().toString();
				this.project_seqFileWriter.append(new Text(key), new BytesWritable(data.toByteArray()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.closeWrite();
	}

	public void store_Demo(List<GeneratedMessage> dataInstance) {
		System.out.println("Inside store Hadoop sequence File Path " + DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/"
				+ DSIProperties.HADOOP_SEQ_FILE_NAME);
		this.openWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/" + DSIProperties.HADOOP_SEQ_FILE_NAME);

		List<File> sources = new ArrayList<File>();
		String path = "/Users/hbagheri/Documents/NCBI/fungi";
		// sources.addAll(Arrays.asList(getGFF(path)));

		// List of folders corresponds to the NCBI refseq
		File[] directories = new File("/Users/hbagheri/Documents/NCBI").listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});

		for (File folder : directories) {
			sources.addAll(Arrays.asList(getGFF(folder.getPath())));
		}
		// sources.addAll(Arrays.asList(directories));

		for (File file : sources) {
			if (file.isFile()) {
				FeatureList fList;
				try {
					fList = GFF3Reader.read(file.getPath());
					set_IDs(new File(file.getPath()));

					String fileName = FilenameUtils.removeExtension(file.getName());
					String assembly_stats = file.getPath().substring(0, file.getPath().indexOf("genomic"))
							+ "assembly_stats.txt";

					List<String> assemblers = get_assemblers(new File(assembly_stats));

					String outFile = file.getParentFile() + "/" + fileName + ".txt";

					Set<String> accessionSet = new HashSet<String>();

					for (FeatureI feature : fList) {
						accessionSet.add(feature.seqname());
					}

					Genome.Builder gb = Genome.newBuilder();
					gb.setTaxid(taxID);
					gb.setRefseq(fileName);

					for (String seq : accessionSet) {
						Sequence.Builder sb = Sequence.newBuilder();

						sb.setAccession(seq);
						sb.setHeader(fileName);
						// sb.setTaxid(taxID);

						Feature.Builder fb = null;
						for (FeatureI feature : fList) {
							fb = Feature.newBuilder();
							if (feature.seqname().equals(seq)) {
								String[] rec = feature.toString().split("\t");
								
								if (feature.hasAttribute("Parent"))
									fb.setParent(feature.getAttribute("Parent"));
								fb.setAccession(rec[0]);
								fb.setSeqid(rec[0]);
								fb.setSource(rec[1]);
								fb.setFtype(rec[2]);
								fb.setStart(Integer.parseInt(rec[3]));
								fb.setEnd(Integer.parseInt(rec[4]));
								fb.setScore(rec[5]);
								fb.setPhase(rec[6]);
								fb.setStrand(String.valueOf(feature.location().bioStrand()));
								Attribute.Builder ab = null;
								// System.out.println(feature);
								for (Map.Entry<String, String> entry : feature.getAttributes().entrySet()) {
									// System.out.println(" tag: " +
									// entry.getKey() + " value: "+
									// entry.getValue());
									ab = Attribute.newBuilder();
									
									ab.setTag(entry.getKey());
									ab.setValue(entry.getValue());
									fb.addAttribute(ab.build());
								}

								// sb.addFeature(fb.build());

							}

						}

						gb.addSequence(sb.build());

					}
					// Bind all assemblers to the current genome file
					// Assembler.Builder ab = null;
					// for (String assembler : assemblers) {
					// ab = Assembler.newBuilder();
					// ab.setRefseq(taxID);
					// String[] data = assembler.split("\\s+");
					// ab.setName(data[1]);
					// ab.setDesc(assembler);
					//
					// gb.addAssembler(ab.build());
					//
					// }

					// Write to the sequence file
					// System.out.println(sb.build());
					// data.add(sb.build());
					try {
						// write message TODO: use text file for save messages.
						// Get the key?
						this.project_seqFileWriter.append(new Text(taxID), new BytesWritable(gb.build().toByteArray()));
						this.assembler_seqFileWriter.append(new Text(taxID),
								new BytesWritable(gb.build().toByteArray()));
						this.feature_seqFileWriter.append(new Text(taxID), new BytesWritable(gb.build().toByteArray()));

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		// ##############

		// for (GeneratedMessage data : dataInstance) {
		// try {
		// // write message TODO: use text file for save messages.
		// // Get the key?
		// String key = Sequence.parseFrom(data.toByteArray()).getAccession();
		// this.seqFileWriter.append(new Text(key), new
		// BytesWritable(data.toByteArray()));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		this.closeWrite();
	}

	public void store_json(String path) {

		File file = new File(path);

		// location of json file on the disk
		BufferedWriter file_modified = null;

		String strJson = null;

		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LineIterator it = null;
		try {
			it = FileUtils.lineIterator(file, "UTF-8");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			while (it.hasNext()) {
				String line = it.nextLine();
				// System.out.println(line);
				// do something with line

				char tax_separator = (char) 65533;// È
				char ann_separator = (char) 1; // "^A"

				strJson = "{";

				// ð starts the cluster info that has appended to the annotation file
				String[] record_line = line.split("ð");
				String[] words = record_line[0].split(" ");
				String key = words[0];

				strJson += "\"seq_id\":\"" + key + "\",";
				strJson += "\"annotations\": [";

				String[] ann = record_line[0].split("");
				// iterate over all annotations
				for (int a = 0; a < ann.length; a++) {

					strJson += "{";

					String keyID = null;
					String defline = "";
					String tax_id = "";
					String tax_name = "";

					keyID = ann[a].substring(0, ann[a].indexOf(" "));

					strJson += "\"key\":\"" + keyID + "\",";

					// System.out.println(ann[i]);
					int first = ann[a].indexOf(tax_separator);
					int second = ann[a].indexOf(tax_separator, first + 1);

					defline = ann[a].substring(ann[a].indexOf(" ", second + 1));

					// add tax id and tax name È....È
					String tax_info = ann[a].substring(first + 1, second);

					//System.out.println(defline);

					defline = defline.replace(tax_separator + tax_info + tax_separator, "").trim();
					strJson += "\"defline\":\"" + defline + "\",";

					if (tax_info.length() > 0) {
						if (tax_info.indexOf(" ") > -1) {
							tax_id = tax_info.substring(0, tax_info.indexOf(" "));
							tax_name = tax_info.substring(tax_info.indexOf(" ") + 1);
						} else {
							System.out.println("### KeyID: " + keyID +  " No taxa name. Tax_infor: " + tax_info);

							tax_id = tax_info;
							tax_name = "";
						}

						strJson += "\"tax_id\":\"" + tax_id + "\",";
						strJson += "\"tax_name\":\"" + tax_name + "\"";
					} else {
						strJson += "\"tax_id\":\"" + "\",";
						strJson += "\"tax_name\":\"" + "\"";
					}

					strJson += "},";

				}

				// remove the last , that appended to the last annotation
				strJson = strJson.substring(0, strJson.length() - 1);

				strJson += "],";

				strJson += "\"clusters\": [";

				if (record_line.length > 1) {
					// FIXME iterate over all clusters
					String[] clusters = record_line[1].split("ü");

					int similiarity = 0;
					for (int c = 0; c < clusters.length; c++) {

						strJson += "{";

						String[] words_clusterInfo = clusters[c].split(" ");
						String[] pos = null;

						String cid = words_clusterInfo[1];

						// find similarity of cd-hit file
						if (c == 0)
							similiarity = 95;
						else if (c == 1)
							similiarity = 90;
						else if (c == 2)
							similiarity = 85;
						else if (c == 3)
							similiarity = 80;
						else if (c == 4)
							similiarity = 75;
						else if (c == 5)
							similiarity = 70;
						else if (c == 6)
							similiarity = 65;

						strJson += "\"similarity\":\"" + similiarity + "\",";

						if (clusters[c].endsWith("*")) {
							strJson += "\"representative\":\"" + true + "\",";

						} else {
							strJson += "\"representative\":" + false + ",";

							pos = words_clusterInfo[3].split(":");
							strJson += "\"seq_start\":\"" + Integer.parseInt(pos[0]) + "\",";
							strJson += "\"seq_stop\":\"" + Integer.parseInt(pos[1]) + "\",";
							strJson += "\"rep_start\":\"" + Integer.parseInt(pos[2]) + "\",";
							strJson += "\"rep_stop\":\"" + Integer.parseInt(pos[3].split("/")[0]) + "\",";
							strJson += "\"match\":\"" + Integer.parseInt(pos[3].split("/")[1].substring(0, 2)) + "\",";

						}

						String[] tmpWord = words_clusterInfo[2].split("\t");
						strJson += "\"length\":\"" + Integer.parseInt(tmpWord[1].substring(0, tmpWord[1].indexOf("aa")))
								+ "\"";

						strJson += "},";

					}

				} else {
					// TODO: why they don't belong to any cluster?
					// System.out.println("No Cluster: " + ann[0]);
				}

				// remove the last , that appended to the last cluster
				strJson = strJson.substring(0, strJson.length() - 1);

				strJson += "]}";

				//System.out.println(strJson);

				// write the json recod to the file.
				try {
					FileWriter fstream = new FileWriter(file.getAbsolutePath() + ".json", true); // true for append
					file_modified = new BufferedWriter(fstream);
					file_modified.write(strJson + "\n");
				}

				catch (IOException e) {
					System.err.println("Error: " + e.getMessage());
				}

				finally {
					if (file_modified != null) {
						try {
							file_modified.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

		} finally {
			LineIterator.closeQuietly(it);
		}

	}

	public void store_joined(String path) {

		System.out.println("Inside store Hadoop sequence File Path " + DSIProperties.HADOOP_SEQ_FILE_LOCATION + location
				+ DSIProperties.HADOOP_ANNOTAIONS_FILE_NAME);

		this.openAnnotationWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + location);

		File file = new File(path);

		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boa.types.Nr2.Sequence.Builder sb = null;

		LineIterator it = null;
		try {
			it = FileUtils.lineIterator(file, "UTF-8");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			while (it.hasNext()) {
				String line = it.nextLine();
				//System.out.println(line);

				char tax_separator = (char) 65533;// È
				char ann_separator = (char) 1; // "^A"
				
				// ð starts the cluster info that has appended to the annotation file
				String[] record_line = line.split("ð");
				String[] words = record_line[0].split(" ");
				String key = words[0];
				sb = boa.types.Nr2.Sequence.newBuilder();
				sb.setSeqid(key);

				boa.types.Nr2.Annotation.Builder ab = null;

				String[] ann = record_line[0].split("");
				// iterate over all annotations
				for (int a = 0; a < ann.length; a++) {

					String keyID = null;
					String defline = "";
					String tax_id = "";
					String tax_name = "";

					ab = Annotation.newBuilder();

					try {
						keyID = ann[a].substring(0, ann[a].indexOf(" "));

						// System.out.println(ann[i]);
						int first = ann[a].indexOf(tax_separator);
						int second = ann[a].indexOf(tax_separator, first + 1);
								

						defline = ann[a].substring(ann[a].indexOf(" ", second + 1));

						// add tax id and tax name È....È
						String tax_info = ann[a].substring(first + 1, second);
						defline = defline.replace(tax_separator + tax_info + tax_separator, "").trim();
						
						if (tax_info.length() > 0) {
							if (tax_info.indexOf(" ") > -1) {
								tax_id = tax_info.substring(0, tax_info.indexOf(" "));
								tax_name = tax_info.substring(tax_info.indexOf(" ") + 1);
							} else {
								tax_id = tax_info;
								tax_name = "";
							}
							

							ab.setTaxId(tax_id);
							ab.setTaxName(tax_name);
						}

					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(ann[a]);
						System.out.println(e);
					}

					ab.setKeyID(keyID);
					ab.setDefline(defline.trim());

					sb.addAnnotation(ab);

				}

				// sb.setDefline(ann[0].substring(ann[0].indexOf(" ")+1));
				// System.out.println("key: " + key);

				boa.types.Nr2.Cluster.Builder cb = null;

				if (record_line.length > 1) {
					// FIXME iterate over all clusters
					String[] clusters = record_line[1].split("ü");

					int similiarity = 0;
					for (int c = 0; c < clusters.length; c++) {
						String[] words_clusterInfo = clusters[c].split(" ");
						String[] pos = null;

						String cid = words_clusterInfo[1];
						cb = boa.types.Nr2.Cluster.newBuilder();
						cb.setCid(cid);

						// find similarity of cd-hit file
						if (c == 0)
							similiarity = 95;
						else if (c == 1)
							similiarity = 90;
						else if (c == 2)
							similiarity = 85;
						else if (c == 3)
							similiarity = 80;
						else if (c == 4)
							similiarity = 75;
						else if (c == 5)
							similiarity = 70;
						else if (c == 6)
							similiarity = 65;

						cb.setSimilarity(similiarity);

						if (clusters[c].endsWith("*")) {
							cb.setRepresentative(true);
						} else {
							cb.setRepresentative(false);
							pos = words_clusterInfo[3].split(":");
							cb.setSeqStart(Integer.parseInt(pos[0]));
							cb.setSeqStop(Integer.parseInt(pos[1]));
							cb.setRepStart(Integer.parseInt(pos[2]));
							cb.setRepStop(Integer.parseInt(pos[3].split("/")[0]));
							cb.setMatch(Integer.parseInt(pos[3].split("/")[1].substring(0, 2)));

						}

						String[] tmpWord = words_clusterInfo[2].split("\t");
						int cl_length = Integer.parseInt(tmpWord[1].substring(0, tmpWord[1].indexOf("aa")));
						cb.setLength(cl_length);

						sb.addCluster(cb);
					}

				} else {
					// TODO: why they don't belong to any cluster?
					// System.out.println("No Cluster: " + ann[0]);
				}

				try {
					this.annotations_seqFileWriter.append(new Text(String.valueOf(key)),
							new BytesWritable(sb.build().toByteArray()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} finally {
			LineIterator.closeQuietly(it);
		}

		this.closeWrite();

	}

//	public void store_annotations(String path){
//
//
//		System.out.println("Inside store Hadoop sequence File Path " + DSIProperties.HADOOP_SEQ_FILE_LOCATION + location
//				+ DSIProperties.HADOOP_ANNOTAIONS_FILE_NAME);
//
//		this.openAnnotationWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + location );
//		
//		File file= new File(path);
//		
//		Scanner sc = null;
//		try {
//			sc = new Scanner(file);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		boa.types.Nr.Annotation.Builder ab = null;
//		
//		while (sc.hasNextLine()){
//			String line = sc.nextLine();
//			
//			String[] ann= line.split("");
//			String[] words = ann[0].split(" ");
//			String key =words[0].substring(words[0].indexOf(">")+1);
//			
//			ab= Annotation.newBuilder();
//			ab.setSeqid(key);
//			ab.setDefline(line);
//			//System.out.println("key: " + key);
//			
//			try {
//				this.annotations_seqFileWriter.append(new Text(String.valueOf(key)),
//						new BytesWritable(ab.build().toByteArray()));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			
//			
//		}
//		
//		
//		this.closeWrite();
//		
//	}

//	public void store_cdhit(String path){
//
//		// TODO Auto-generated method stub
//
//		System.out.println("Inside store Hadoop sequence File Path " + DSIProperties.HADOOP_SEQ_FILE_LOCATION  + location 
//				+ DSIProperties.HADOOP_NR_FILE_NAME);
//
//		this.openNRWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + location);
//		
//		
//
//		File file = new File(path);
//		Scanner sc = null;
//		try {
//			sc = new Scanner(file);
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String line1 = "", line2 = "";
//		int clusterID = -1;
//		String[] pos=null;
//
//
//		boa.types.Nr.Sequence.Builder sb = null;
//		Cluster.Builder cb = null;
//
//		while (sc.hasNextLine()) {
//			line1 = sc.nextLine();
//			
////			String cmd= "less env_nr_annotation2 | grep \"VAX37673.1\"";
////			String reads= runCmd(cmd);
////			System.out.println(reads);
//
//			if (line1.startsWith(">")) {
//				System.out.println(line1);
//
//				if (clusterID != -1) { // new record must save cluster and all sequences belong to this cluster
//					// write the record to the disk
//
//					try {
//						this.nr_seqFileWriter.append(new Text(String.valueOf(clusterID)),
//								new BytesWritable(cb.build().toByteArray()));
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//				}
//
//				cb = Cluster.newBuilder();
//
//				clusterID = Integer.parseInt(line1.substring(line1.indexOf(" ") + 1));
//				cb.setId(line1.substring(line1.indexOf(" ") + 1)); // cluster id eg. >Cluster 4
//				cb.setSimilarity(95); //FIXME different similarity level
//
//				line2 = sc.nextLine();
//
//				if (!line2.startsWith(">")) {
//
//					// same cluster
//
//					String[] rep = line2.split("\\s+");
//					int cl_length = Integer.parseInt(rep[1].substring(0, rep[1].indexOf("aa")));
//					String rep_id = rep[2].substring(1, rep[2].indexOf("..."));
//					// representative sample
//					if (line2.endsWith("*")) {
//						System.out.println("rep: " + line2);
//
//						cb.setSeqid(rep_id);
//						cb.setLength(cl_length);
//
//					} else { // sequences belong to the current cluster
//						sb = boa.types.Nr.Sequence.newBuilder();
//						sb.setSeqid(rep_id);
//						sb.setLength(cl_length);
//						//sb.setSeq(line2);  //not needed at this point
//						
//						pos= rep[4].split(":");		
//						sb.setStart(Integer.parseInt(pos[0]));
//						sb.setStop(Integer.parseInt(pos[1]));
//						sb.setRepStart(Integer.parseInt(pos[2]));
//						sb.setRepStop(Integer.parseInt(pos[3].split("/")[0]));
//						sb.setSimilarity(Integer.parseInt(pos[3].split("/")[1].substring(0,2)));
//						sb.setTaxonomy(""); // FIXME taxonomy name
//
//						cb.addSequence(sb);
//					}
//				}
//
//			} else {
//				// same cluster:
//
//				String[] rep = line1.split("\\s+");
//				int cl_length = Integer.parseInt(rep[1].substring(0, rep[1].indexOf("aa")));
//				String rep_id = rep[2].substring(1, rep[2].indexOf("..."));
//
//				if (line1.endsWith("*")) { // representative sequence
//
//					cb.setSeqid(rep_id);
//					cb.setLength(cl_length);
//
//				} else { // sequence
//
//					sb = boa.types.Nr.Sequence.newBuilder();
//					sb.setSeqid(rep_id);
//					sb.setLength(cl_length);
//					//sb.setSeq(line2); 
//					pos= rep[4].split(":");		
//					sb.setStart(Integer.parseInt(pos[0]));
//					sb.setStop(Integer.parseInt(pos[1]));
//					sb.setRepStart(Integer.parseInt(pos[2]));
//					sb.setRepStop(Integer.parseInt(pos[3].split("/")[0]));
//					sb.setSimilarity(Integer.parseInt(pos[3].split("/")[1].substring(0,2)));
//					sb.setTaxonomy(""); // FIXME
//
//					cb.addSequence(sb);
//				}
//
//			}
//
//		}
//
//	
//
//		this.closeWrite();
//		
//	}

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

	@Override
	public void store(String path) {
		
		System.out.println("Inside store Hadoop sequence File Path " + DSIProperties.HADOOP_SEQ_FILE_LOCATION + location
				+ DSIProperties.HADOOP_SEQ_FILE_NAME);

		this.openRefSeqWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + location);
		

		List<File> GFF_fileList = new ArrayList<File>();

		// List of folders corresponds to the NCBI refseq
		File[] directories = new File(path).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});

		if (directories.length == 0) {
			directories = new File[1];
			directories[0] = new File(path);
		}
		for (File folder : directories) {
			GFF_fileList.addAll(Arrays.asList(getGFF(folder.getPath())));
		}
		// sources.addAll(Arrays.asList(directories));

		PriorityQueue<String> seqQueue = new PriorityQueue<String>();
		HashMap<String, GeneratedMessage> seqMap = new HashMap<String, GeneratedMessage>();

		HashMap<String, File> filemap = new HashMap<String, File>();
		for (File file : GFF_fileList) {
			filemap.put(file.getName(), file);
		}

		Map<String, File> sortedmap = new TreeMap(filemap);

		GFF_fileList.clear();
		for (Map.Entry<String, File> entry : sortedmap.entrySet()) {
			GFF_fileList.add(entry.getValue());
		}

		for (File file : GFF_fileList) {

			System.out.println("File Name: " + file.getName());

			if (file.isFile()) {

				seqQueue.clear();
				seqMap.clear();

				FeatureList fList;
				try {
					fList = GFF3Reader.read(file.getPath());

					set_IDs(new File(file.getPath()));

					String fileName = FilenameUtils.removeExtension(file.getName());
					// String refseqID = fileName.substring(0, fileName.indexOf("genomic") - 1);
					String assembly_stats = file.getPath().substring(0, file.getPath().indexOf("genomic"))
							+ "assembly_stats.txt";
					List<String> assemblers = get_assemblers(new File(assembly_stats));

					Genome.Builder gb = Genome.newBuilder();
					gb.setTaxid(taxID);
					gb.setRefseq(refseqID);

					String currAccessionID = null;
					Sequence.Builder sb = null;
					FeatureRoot.Builder frb = null;
					Feature.Builder fb = null;

					for (FeatureI feature : fList) {

						if (currAccessionID == null) {
							// new sequence region
							sb = Sequence.newBuilder();
							frb = FeatureRoot.newBuilder();

							currAccessionID = feature.seqname();

							sb.setAccession(currAccessionID);
							sb.setHeader(fileName);
							
							frb.setRefseq(refseqID + "_" + currAccessionID);

						} else if (!currAccessionID.equals(feature.seqname())) {
							gb.addSequence(sb.build());

							frb.setRefseq(refseqID + "_" + currAccessionID);

							// put to the priority Queue to sort based on the Key
							seqQueue.add(refseqID + "_" + currAccessionID);
							seqMap.put(refseqID + "_" + currAccessionID, frb.build());

							// new sequence region
							sb = Sequence.newBuilder();
							frb = FeatureRoot.newBuilder();

							currAccessionID = feature.seqname();
							sb.setAccession(currAccessionID);
							sb.setHeader(fileName);
							
							frb.setRefseq(refseqID + "_" + currAccessionID);
						}

						// the same sequence region
						// it is a feature just add to the new FeatureRoot
						fb = Feature.newBuilder();
						String[] rec = feature.toString().split("\t");
						
						if (feature.hasAttribute("Parent")) {
							fb.setParent(feature.getAttribute("Parent"));
						}
						fb.setAccession(rec[0]);
						fb.setSeqid(rec[0]);
						fb.setSource(rec[1]);
						fb.setFtype(rec[2]);
						fb.setStart(Integer.parseInt(rec[3]));
						fb.setEnd(Integer.parseInt(rec[4]));
						fb.setScore(rec[5]);
						fb.setPhase(rec[6]);
						fb.setStrand(String.valueOf(feature.location().bioStrand()));
						Attribute.Builder attb = null;
						for (Map.Entry<String, String> entry : feature.getAttributes().entrySet()) {
							attb = Attribute.newBuilder();
							attb.setTag(entry.getKey());
							attb.setValue(entry.getValue());
							fb.addAttribute(attb.build());
						}

						frb.addFeature(fb.build());
					}

					// IMP: save the last segment to the gb and save the
					// featureroot before that
					// Have to save in another seq file:
					// sb.setFeatureRoot(frb.build());
					gb.addSequence(sb.build());

					// put to the priority Queue to sort based on the Key
					seqQueue.add(refseqID + "_" + currAccessionID);
					seqMap.put(refseqID + "_" + currAccessionID, frb.build());

					// Bind all assemblers to the current genome file
					AssemblerRoot.Builder arb = AssemblerRoot.newBuilder();
					arb.setRefseq(refseqID);
					arb.setContigCount(contig_count);
					arb.setContigN50(contig_N50);
					arb.setScaffoldCount(scaffold_count);
					arb.setScaffoldN50(scaffold_N50);
					arb.setTotalGapLength(total_gap_length);
					arb.setTotalLength(total_length);
					arb.setAssemblyDate(assembly_date);

					Assembler.Builder asb = null;
					for (String assembler : assemblers) {
						asb = Assembler.newBuilder();
						
						try {
							String[] data = assembler.split("\\s+");
							if (data.length >1) {
							asb.setName(data[1]); //the first word would be assembler name
							}else
							{
								asb.setName("");
							}
							// the rest of the string would be version etc about the assembler program
							// only if the number of words are >2
							if (data.length>2)
							   asb.setDesc(assembler.substring(assembler.indexOf(data[2]))); 
							else
								asb.setDesc("");
							arb.addAssembler(asb.build());
						} catch (Exception e) {
							// TODO: handle exception
						}
						

					}

					// No need to save this as a gb because assemblers.seq will
					// be another seq file
					// gb.setAssemblerRoot(arb.build());

					try {
						// FIXME Avoid overflowing the int too early by casting
						// to a long.
						// long newSize = Math.min(Integer.MAX_VALUE, (3L *
						// gb.build().toByteArray().length) / 2L);
						// BytesWritable dataValue = new BytesWritable();
						// dataValue.setCapacity((int) newSize);
						// dataValue.setSize((int) newSize);
						// dataValue.set(new BytesWritable(
						// gb.build().toByteArray()));

						this.project_seqFileWriter.append(new Text(refseqID),
								new BytesWritable(gb.build().toByteArray()));

					} catch (InvalidProtocolBufferException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					while (seqQueue.size() != 0) {
						String key = seqQueue.remove();
						GeneratedMessage froot = seqMap.get(key);

						try {

							this.feature_seqFileWriter.append(new Text(key), new BytesWritable(froot.toByteArray()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					try {

						this.assembler_seqFileWriter.append(new Text(refseqID),
								new BytesWritable(arb.build().toByteArray()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		this.closeRefSeqWrite();
	}

	
public void store_RefSeq_Json(String path) {
		
		
		File file_path = new File(path+"_converted");
	
		// location of json file on the disk
		BufferedWriter file_modified = null;
	
		String strJson = "";

		List<File> GFF_fileList = new ArrayList<File>();

		// List of folders corresponds to the NCBI refseq
		File[] directories = new File(path).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.isDirectory();
			}
		});

		if (directories.length == 0) {
			directories = new File[1];
			directories[0] = new File(path);
		}
		for (File folder : directories) {
			GFF_fileList.addAll(Arrays.asList(getGFF(folder.getPath())));
		}
		// sources.addAll(Arrays.asList(directories));


		HashMap<String, File> filemap = new HashMap<String, File>();
		for (File file : GFF_fileList) {
			filemap.put(file.getName(), file);
		}

		Map<String, File> sortedmap = new TreeMap(filemap);

		GFF_fileList.clear();
		for (Map.Entry<String, File> entry : sortedmap.entrySet()) {
			GFF_fileList.add(entry.getValue());
		}

		for (File file : GFF_fileList) {

			System.out.println("File Name: " + file.getName());

			if (file.isFile()) {

				

				FeatureList fList;
				try {
					
					strJson ="{";
					
					fList = GFF3Reader.read(file.getPath());

					set_IDs(new File(file.getPath()));

					String fileName = FilenameUtils.removeExtension(file.getName());
					// String refseqID = fileName.substring(0, fileName.indexOf("genomic") - 1);
					String assembly_stats = file.getPath().substring(0, file.getPath().indexOf("genomic"))
							+ "assembly_stats.txt";
					List<String> assemblers = get_assemblers(new File(assembly_stats));
					
					strJson += "\"seqid\":\"" + refseqID + "\",";
					strJson += "\"taxid\":\"" + taxID + "\",";

					strJson += "\"feature\" :[";

					String currAccessionID = null;

					
					for (FeatureI feature : fList) {
						
						// write the json recod to the file and then clear the strJson variable
						try {
							FileWriter fstream = new FileWriter(file_path.getAbsolutePath() + ".json", true); // true for append
							file_modified = new BufferedWriter(fstream);
							file_modified.write(strJson + "\n");
							
							strJson=""; //we already have written all the info we had so far.
						}

						catch (IOException e) {
							System.err.println("Error: " + e.getMessage());
						}

						finally {
							if (file_modified != null) {
								try {
									file_modified.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
						

						strJson += "{";
						
						if (currAccessionID == null) {
							// new sequence region

							currAccessionID = feature.seqname();


						} else if (!currAccessionID.equals(feature.seqname())) {

							currAccessionID = feature.seqname();
				
						}

						// the same sequence region
						// it is a feature just add to the new FeatureRoot
						String[] rec = feature.toString().split("\t");
						
						strJson += "\"id\": \"" + feature.getAttribute("ID") + "\",";
						
						if (feature.hasAttribute("Parent")) {
							//fb.setParent(feature.getAttribute("Parent"));
						}						
						
						strJson += "\"accession\": \"" + rec[0] + "\",";
						strJson += "\"source\": \"" + rec[1] + "\",";
						strJson += "\"ftype\": \"" + rec[2] + "\",";
						strJson += "\"start\": \"" + rec[3] + "\",";
						strJson += "\"end\": " + rec[4] + ",";
						strJson += "\"score\": \"" + rec[5] + "\",";
						strJson += "\"phase\": \"" + rec[6] + "\",";
						strJson += "\"strand\": \"" + rec[0] + "\",";
						
						strJson += "\"attribute\": ["  ;
						for (Map.Entry<String, String> entry : feature.getAttributes().entrySet()) {
							strJson += "{";							
							strJson += "\"tag\": \"" + entry.getKey() + "\",";
							strJson += "\"value\": \"" + entry.getValue() + "\"";
							strJson += "},";
						}

						strJson = strJson.substring(0, strJson.length() - 1);
						strJson +="]} ," ;
						
						
						
						
						
						
						
					}

					strJson = strJson.substring(0, strJson.length() - 1);
					strJson += "],";
					
					// IMP: save the last segment to the gb and save the
					// featureroot before that
					// Have to save in another seq file:
					// sb.setFeatureRoot(frb.build());
					
					strJson += "\"assembler\": {";

					strJson += "\"contig_count\": " + contig_count + ",";
					
					strJson += "\"contig_N50\": " + contig_N50 + ",";
					strJson += "\"scaffold_count\": " + scaffold_count + ",";

					strJson += "\"scaffold_N50\": " + scaffold_N50 + ",";
					strJson += "\"total_gap_length\": " + total_gap_length + ",";
					strJson += "\"total_length\": " + total_length + ",";
 
					strJson += "\"assembler\": [";
					for (String assembler : assemblers) {
						
						strJson += " {";
						
						String[] data = assembler.split("\\s+");
						strJson += "\"name\": \"" + data[1] + "\",";

						strJson += "\"desc\": \"" + data[1] + "\"";

						strJson += "},";

					}
					strJson = strJson.substring(0,strJson.length()-1);
					strJson += "]} }";

					
					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			
			// write the json recod to the file.
			try {
				FileWriter fstream = new FileWriter(file_path.getAbsolutePath() + ".json", true); // true for append
				file_modified = new BufferedWriter(fstream);
				file_modified.write(strJson + "\n");
			}

			catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
			}

			finally {
				if (file_modified != null) {
					try {
						file_modified.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			
		}
		

	}


	// @Override
	// public void store(String path) {
	// System.out.println("Inside store Hadoop sequence File Path
	// "+DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/" +
	// DSIProperties.HADOOP_SEQ_FILE_NAME);
	// this.openWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/" +
	// DSIProperties.HADOOP_SEQ_FILE_NAME);
	//
	//
	//
	// List<File> sources = new ArrayList<File>();
	// //sources.addAll(Arrays.asList(getGFF(path)));
	//
	// //List of folders corresponds to the NCBI refseq
	// File[] directories = new File(path).listFiles(new FileFilter() {
	// @Override
	// public boolean accept(File file) {
	// return file.isDirectory();
	// }
	// });
	//
	// if (directories.length==0){
	// directories=new File[1];
	// directories[0]=new File(path);
	// }
	// for (File folder:directories){
	// sources.addAll(Arrays.asList(getGFF(folder.getPath())));
	// }
	// //sources.addAll(Arrays.asList(directories));
	//
	//
	// for (File file : sources) {
	// if (file.isFile()) {
	// FeatureList fList;
	// try {
	// fList = GFF3Reader.read(file.getPath());
	//
	// String taxID = get_taxID(new File(file.getPath()));
	//
	// String fileName = FilenameUtils.removeExtension(file.getName());
	// String refseq=fileName.substring(0,fileName.indexOf("genomic")-1);
	// String assembly_stats = file.getPath().substring(0,
	// file.getPath().indexOf("genomic"))
	// + "assembly_stats.txt";
	// List<String> assemblers = get_assemblers(new File(assembly_stats));
	//
	// String outFile = file.getParentFile() + "/" + fileName + ".txt";
	//
	// Set<String> accessionSet = new HashSet<String>();
	//
	// Genome.Builder gb = Genome.newBuilder();
	// gb.setTaxid(taxID);
	// gb.setRefseq(refseq);
	// gb.setContigCount(contig_count);
	// gb.setContigN50(contig_N50);
	// gb.setScaffoldCount(scaffold_count);
	// gb.setScaffoldN50(scaffold_N50);
	// gb.setTotalGapLength(total_gap_length);
	// gb.setTotalLength(total_length);
	//
	//// for (FeatureI feature : fList) {
	//// accessionSet.add(feature.seqname());
	//// }
	//
	// String currKey=null;
	// Sequence.Builder sb=null;
	//
	// for (FeatureI feature : fList) {
	//
	// if (currKey==null){
	// //new sequence region
	// sb = Sequence.newBuilder();
	// currKey=feature.seqname();
	// sb.setAccession(feature.seqname());
	// sb.setHeader(fileName);
	// sb.setRefseq(refseq);
	//
	// }
	// else if(!currKey.equals(feature.seqname())){
	// gb.addSequence(sb.build());
	// //new sequence region
	// sb = Sequence.newBuilder();
	// currKey=feature.seqname();
	// sb.setAccession(feature.seqname());
	// sb.setHeader(fileName);
	// sb.setRefseq(refseq);
	//
	// }
	// //the same sequence region
	// //it is a feature just add to the new sb
	// Feature.Builder fb = Feature.newBuilder();
	// String[] rec = feature.toString().split("\t");
	// fb.setId(feature.getAttribute("ID"));
	// if (feature.hasAttribute("Parent")){
	// fb.setParent(feature.getAttribute("Parent"));
	// }
	// fb.setAccession(rec[0]);
	// fb.setSeqid(rec[0]);
	// fb.setSource(rec[1]);
	// fb.setFtype(rec[2]);
	// fb.setStart(Integer.parseInt(rec[3]));
	// fb.setEnd(Integer.parseInt(rec[4]));
	// fb.setScore(rec[5]);
	// fb.setPhase(rec[6]);
	// fb.setStrand(String.valueOf(feature.location().bioStrand()));
	// Attribute.Builder ab = null;
	// for (Map.Entry<String, String> entry :
	// feature.getAttributes().entrySet()) {
	// ab = Attribute.newBuilder();
	// ab.setId(feature.getAttribute("ID"));
	// ab.setTag(entry.getKey());
	// ab.setValue(entry.getValue());
	// fb.addAttribute(ab.build());
	// }
	// sb.addFeature(fb.build());
	//
	//
	// }
	//
	// //save the last seqment to the gb
	// gb.addSequence(sb.build());
	//
	//
	//
	//// for (String seq : accessionSet) {
	//// Sequence.Builder sb = Sequence.newBuilder();
	//// sb.setAccession(seq);
	//// sb.setHeader(fileName);
	//// sb.setTaxid(taxID);
	////
	//// Feature.Builder fb = null;
	//// Iterator<FeatureI> it = fList.iterator();
	//// for (FeatureI feature : fList) {
	//// fb = Feature.newBuilder();
	//// if (feature.seqname().equals(seq)) {
	//// String[] rec = feature.toString().split("\t");
	//// fb.setId(feature.getAttribute("ID"));
	//// if (feature.hasAttribute("Parent"))
	//// fb.setParent(feature.getAttribute("Parent"));
	//// fb.setAccession(rec[0]);
	//// fb.setSeqid(rec[0]);
	//// fb.setSource(rec[1]);
	//// fb.setFtype(rec[2]);
	//// fb.setStart(Integer.parseInt(rec[3]));
	//// fb.setEnd(Integer.parseInt(rec[4]));
	//// fb.setScore(rec[5]);
	//// fb.setPhase(rec[6]);
	//// fb.setStrand(String.valueOf(feature.location().bioStrand()));
	//// Attribute.Builder ab = null;
	//// for (Map.Entry<String, String> entry :
	// feature.getAttributes().entrySet()) {
	//// ab = Attribute.newBuilder();
	//// ab.setId(feature.getAttribute("ID"));
	//// ab.setTag(entry.getKey());
	//// ab.setValue(entry.getValue());
	//// fb.addAttribute(ab.build());
	//// }
	//// sb.addFeature(fb.build());
	//// }
	//// }
	////
	//// gb.addSequence(sb.build());
	//// }
	// // Bind all assemblers to the current genome file
	// Assembler.Builder ab = null;
	// for (String assembler : assemblers) {
	// ab = Assembler.newBuilder();
	// ab.setRefseq(refseq);
	// String[] data = assembler.split("\\s+");
	// ab.setName(data[1]);
	// ab.setDesc(assembler);
	//
	// gb.addAssembler(ab.build());
	//
	// }
	//
	// // Write to the sequence file
	// try {
	// // Avoid overflowing the int too early by casting to a long.
	//// long newSize = Math.min(Integer.MAX_VALUE, (3L *
	// gb.build().toByteArray().length) / 2L);
	////
	//// BytesWritable dataValue = new BytesWritable();
	//// dataValue.setCapacity((int) newSize);
	//// dataValue.setSize((int) newSize);
	//// dataValue.set(new BytesWritable( gb.build().toByteArray()));
	//
	// this.seqFileWriter.append(new Text(refseq), new
	// BytesWritable(gb.build().toByteArray()));
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// }
	//
	// this.closeWrite();
	// }

	private static File[] getGFF(String path) {
		File dir = new File(path);
		File[] gffFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".gff");
			}
		});
		return gffFiles;

	}

	private static void set_IDs(File file) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));

			String line = "";
			try {
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("##species ")) {
						taxID = line.substring(line.indexOf("id=") + 3);

					}

					if (line.startsWith("#!genome-build-accession NCBI_Assembly:")) {
						refseqID = line.substring(line.indexOf(":") + 1);

					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static List<String> get_assemblers(File file) {

		
		total_length = 0;
		total_gap_length = 0;
		contig_N50 = 0;
		scaffold_N50 = 0;
		contig_count = 0;
		scaffold_count = 0;
		assembly_date =0;

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();


		List<String> assemblers = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file.getAbsolutePath()));

			String line = "";
			try {
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("# Assembly method:")) {

						String[] data = line.substring(line.indexOf("# Assembly method: ") + 18).split(";");
						for (int i = 0; i < data.length; i++) {
							assemblers.add(data[i]);
						}
//						if (reader != null) {
//							reader.close();
//						}
//						return assemblers;
					}
					if (line.startsWith("# Date")) {
						String[] words =line.split(" ");
						String strDate = words[words.length-1].trim(); 
						Date dtime = null;
						try {
							 dtime = ft.parse(strDate);
						} catch (ParseException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						
						cal.setTime(dtime);
						assembly_date =dtime.getTime();

						//System.out.println("dtime: "+  dtime.toString() + " get Year: " + cal.get(Calendar.YEAR));
						
						
						
					}
					if (line.startsWith("all")) {
						String[] data = line.split("\t");
						// System.out.println(data[4] + ": " +data[5]);
						if (data[4].equals("scaffold-count")) {
							scaffold_count = Long.valueOf(data[5]);

						} else if (data[4].equals("scaffold-N50")) {
							scaffold_N50 = Long.valueOf(data[5]);

						} else if (data[4].equals("contig-count")) {
							contig_count = Long.valueOf(data[5]);

						} else if (data[4].equals("contig-N50")) {
							contig_N50 = Long.valueOf(data[5]);

						} else if (data[4].equals("total-gap-length")) {
							total_gap_length = Long.valueOf(data[5]);
						} else if (data[4].equals("total-length")) {
							total_length = Long.valueOf(data[5]);

						}

					}

					// genome length

					// N50
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (assemblers.isEmpty()) 
				assemblers.add(" N/A");
		if (reader != null)
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return assemblers;
	}

	// @Override
	// public void store(List<GeneratedMessage> dataInstance) {
	// this.openWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/" +
	// DSIProperties.HADOOP_SEQ_FILE_NAME);
	// for (GeneratedMessage data : dataInstance) {
	// try {
	// // write message TODO: use text file for save messages.
	// //Get the key?
	// String key= Sequence.parseFrom(data.toByteArray()).getAccession();
	// this.seqFileWriter.append(new Text(key), new
	// BytesWritable(data.toByteArray()));
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// this.closeWrite();
	// }

	@Override
	public void storeAt(String location, GeneratedMessage dataInstance) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getDataInQueue(Queue<GeneratedMessage> q) {
		return false;
	}

	@Override
	public List<GeneratedMessage> getData() {
		this.openReader(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/" + DSIProperties.HADOOP_SEQ_FILE_NAME);
		List<GeneratedMessage> data = new ArrayList<GeneratedMessage>();

		org.apache.hadoop.io.Text key = (org.apache.hadoop.io.Text) ReflectionUtils
				.newInstance(this.seqFileReader.getKeyClass(), conf);
		org.apache.hadoop.io.BytesWritable keyValue = (org.apache.hadoop.io.BytesWritable) ReflectionUtils
				.newInstance(this.seqFileReader.getValueClass(), conf);

		try {
			while (this.seqFileReader.next(key, keyValue)) {
				data.add(this.parserSource.parseFrom(com.google.protobuf.CodedInputStream
						.newInstance(keyValue.getBytes(), 0, keyValue.getLength())));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return data;
	}

	private boolean openReader(String seqPath) {
		Path path = new Path(seqPath);
		FileSystem fs;
		try {
			fs = FileSystem.get(conf);
			this.seqFileReader = new SequenceFile.Reader(fs, path, conf);
			return true;
		} catch (IOException e) {
			System.out.println("Exception occured in Program node while creating FileSystem or Reader");
			e.printStackTrace();
			return false;
		}
	}

	protected boolean openRefSeqWriter(String seqPath) {
		FileSystem fileSystem;
		try {
			fileSystem = FileSystem.get(conf);
			this.project_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
					new Path(seqPath + DSIProperties.HADOOP_SEQ_FILE_NAME), Text.class, BytesWritable.class);

			this.assembler_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
					new Path(seqPath + DSIProperties.HADOOP_Assembler_FILE_NAME), Text.class, BytesWritable.class);

			this.feature_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
					new Path(seqPath + DSIProperties.HADOOP_Feature_FILE_NAME), Text.class, BytesWritable.class);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected boolean openWriter(String seqPath) {
		FileSystem fileSystem;
		try {
			fileSystem = FileSystem.get(conf);
			this.project_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
					new Path(seqPath + DSIProperties.HADOOP_SEQ_FILE_NAME), Text.class, BytesWritable.class);

			this.assembler_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
					new Path(seqPath + DSIProperties.HADOOP_Assembler_FILE_NAME), Text.class, BytesWritable.class);

			this.feature_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
					new Path(seqPath + DSIProperties.HADOOP_Feature_FILE_NAME), Text.class, BytesWritable.class);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean openNRWriter(String seqPath) {
		FileSystem fileSystem;
		try {
			fileSystem = FileSystem.get(conf);
//			this.project_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
//					new Path(seqPath + DSIProperties.HADOOP_SEQ_FILE_NAME), Text.class, BytesWritable.class);
//
//			this.assembler_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
//					new Path(seqPath + DSIProperties.HADOOP_Assembler_FILE_NAME), Text.class, BytesWritable.class);
//
//			this.feature_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
//					new Path(seqPath + DSIProperties.HADOOP_Feature_FILE_NAME), Text.class, BytesWritable.class);

			this.nr_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
					new Path(seqPath + DSIProperties.HADOOP_NR_FILE_NAME), Text.class, BytesWritable.class);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	protected boolean openAnnotationWriter(String seqPath) {
		FileSystem fileSystem;
		try {
			fileSystem = FileSystem.get(conf);

			this.annotations_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
					new Path(seqPath + DSIProperties.HADOOP_ANNOTAIONS_FILE_NAME), Text.class, BytesWritable.class);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean closeReader() {
		try {
			seqFileReader.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	protected boolean closeRefSeqWrite() {
		try {
			
			project_seqFileWriter.close();
			assembler_seqFileWriter.close();
			feature_seqFileWriter.close();

			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	protected boolean closeWrite() {
		try {
			// FIXME: temporarily disables because of NR database
//			project_seqFileWriter.close();
//			assembler_seqFileWriter.close();
//			feature_seqFileWriter.close();
//			nr_seqFileWriter.close();
			annotations_seqFileWriter.close();

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public String getDataLocation() {
		return this.location;
	}

	@Override
	public void store(Queue<GeneratedMessage> queue) {
		if (queue == null) {
			throw new UnsupportedOperationException();
		}
		int totalMessages = queue.size();
		ArrayList<GeneratedMessage> msg = new ArrayList<GeneratedMessage>();
		for (int i = 0; i < totalMessages; i++) {
			msg.add(queue.get());
		}
		this.store(msg);
	}

}
