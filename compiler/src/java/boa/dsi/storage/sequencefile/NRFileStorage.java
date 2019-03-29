//package boa.dsi.storage.sequencefile;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//import java.util.Scanner;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.BytesWritable;
//import org.apache.hadoop.io.SequenceFile;
//import org.apache.hadoop.io.Text;
//import org.eclipse.jdt.core.dom.ThisExpression;
//
//import com.aol.cyclops.data.async.Queue;
//import com.google.protobuf.GeneratedMessage;
//import com.google.protobuf.InvalidProtocolBufferException;
//
//import boa.dsi.DSIProperties;
//import boa.dsi.dsource.AbstractSource;
//import boa.dsi.storage.AbstractStorage;
//import boa.types.Nr.Sequence;
//import boa.types.Nr.Cluster;
//
//public class NRFileStorage extends AbstractStorage {
//
//	protected Configuration conf;
//	// SequenceFile.Reader seqFileReader;
//	protected SequenceFile.Writer nr_seqFileWriter;
//
//	public NRFileStorage(String location, AbstractSource parserSource) {
//		super(location, parserSource);
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public List<GeneratedMessage> getData() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean isAvailable(String source) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void store(List<GeneratedMessage> dataInstance) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void store(Queue<GeneratedMessage> queue) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void store(String path) {
//		// TODO Auto-generated method stub
//
//		System.out.println("Inside store Hadoop sequence File Path " + DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/"
//				+ DSIProperties.HADOOP_NR_FILE_NAME);
//
//		this.openWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/");
//		
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
//
//		Sequence.Builder sb = null;
//		Cluster.Builder cb = null;
//
//		while (sc.hasNextLine()) {
//			line1 = sc.nextLine();
//
//			if (line1.startsWith(">")) {
//
//				if (clusterID != -1) { // new record must save cluster and all sequences belong to this cluster
//					// TODO
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
//
//				line2 = sc.nextLine();
//
//				if (!line2.startsWith(">")) {
//
//					// same cluster
//					System.out.println("line1: " + line1);
//					System.out.println("cluster# " + clusterID + " " + line2);
//
//					String[] rep = line2.split("\\s+");
//					int cl_length = Integer.parseInt(rep[1].substring(0, rep[1].indexOf("aa")));
//					String rep_id = rep[2].substring(1, rep[2].indexOf("..."));
//					// representative sample
//					if (line2.endsWith("*")) {
//						System.out.println("Representative of the cluster");
//
//						System.out.println(rep_id);
//						System.out.println(cl_length);
//						cb.setSeqid(rep_id);
//						cb.setLength(cl_length);
//
//					} else { // sequences belong to the current cluster
//						System.out.println("seq id: " + rep_id);
//						sb = Sequence.newBuilder();
//						sb.setSeqid(rep_id);
//						sb.setLength(cl_length);
//						sb.setSeq(line2); // FIXME: must be the sequence form the original database
//						sb.setStart(0);
//						sb.setStop(0);
//						sb.setRepStart(0);
//						sb.setRepStop(0);
//						sb.setSimilarity(0);
//						sb.setTaxonomy(""); // FIXME
//
//						cb.addSequence(sb);
//					}
//				}
//
//			} else {
//				// same cluster:
//				System.out.println("cluster# " + clusterID + " " + line1);
//
//				String[] rep = line1.split("\\s+");
//				int cl_length = Integer.parseInt(rep[1].substring(0, rep[1].indexOf("aa")));
//				String rep_id = rep[2].substring(1, rep[2].indexOf("..."));
//
//				if (line1.endsWith("*")) { // representative sequence
//					System.out.println("Representative of the cluster");
//
//					System.out.println(rep_id);
//					System.out.println(cl_length);
//
//					cb.setSeqid(rep_id);
//					cb.setLength(cl_length);
//
//				} else { // sequence
//					System.out.println("seq id: " + rep_id);
//
//					sb = Sequence.newBuilder();
//					sb.setSeqid(rep_id);
//					sb.setLength(cl_length);
//					sb.setSeq(line2); // FIXME: must be the sequence form the original database
//					sb.setStart(0);
//					sb.setStop(0);
//					sb.setRepStart(0);
//					sb.setRepStop(0);
//					sb.setSimilarity(0);
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
//	}
//
//	protected boolean openWriter(String seqPath) {
//		FileSystem fileSystem;
//		try {
//			fileSystem = FileSystem.get(conf);
//			this.nr_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
//					new Path(seqPath + DSIProperties.HADOOP_NR_FILE_NAME), Text.class, BytesWritable.class);
//
//			return true;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	@Override
//	public void storeAt(String location, GeneratedMessage dataInstance) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public String getDataLocation() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////	public static void parseCDHIT(String fname) throws FileNotFoundException {
////
////		File file = new File(fname);
////		Scanner sc = new Scanner(file);
////		String line1 = "", line2 = "";
////		int clusterID = -1;
////
////		Sequence.Builder sb = null;
////		Cluster.Builder cb = null;
////
////		while (sc.hasNextLine()) {
////			line1 = sc.nextLine();
////
////			if (line1.startsWith(">")) {
////
////				if (clusterID != -1) { // new record must save cluster and all sequences belong to this cluster
////					// TODO
////					// write the record to the disk
////
////					try {
////						this.nr_seqFileWriter.append(new Text(String.valueOf(clusterID)),
////								new BytesWritable(cb.build().toByteArray()));
////					} catch (IOException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////
////				}
////
////				cb = Cluster.newBuilder();
////
////				clusterID = Integer.parseInt(line1.substring(line1.indexOf(" ") + 1));
////				cb.setId(line1.substring(line1.indexOf(" ") + 1)); // cluster id eg. >Cluster 4
////
////				line2 = sc.nextLine();
////
////				if (!line2.startsWith(">")) {
////
////					// same cluster
////					System.out.println("line1: " + line1);
////					System.out.println("cluster# " + clusterID + " " + line2);
////
////					String[] rep = line2.split("\\s+");
////					int cl_length = Integer.parseInt(rep[1].substring(0, rep[1].indexOf("aa")));
////					String rep_id = rep[2].substring(1, rep[2].indexOf("..."));
////					// representative sample
////					if (line2.endsWith("*")) {
////						System.out.println("Representative of the cluster");
////
////						System.out.println(rep_id);
////						System.out.println(cl_length);
////						cb.setRepresentative(line2); // FIXME: must be the sequence form the original database
////						cb.setSeqid(rep_id);
////						cb.setLength(cl_length);
////
////					} else { // sequences belong to the current cluster
////						System.out.println("seq id: " + rep_id);
////						sb = Sequence.newBuilder();
////						sb.setSeqid(rep_id);
////						sb.setLength(cl_length);
////						sb.setSeq(line2); // FIXME: must be the sequence form the original database
////						sb.setStart(0);
////						sb.setStop(0);
////						sb.setRepStart(0);
////						sb.setRepStop(0);
////						sb.setSimilarity(0);
////						sb.setTaxonomy(""); // FIXME
////
////						cb.addSequence(sb);
////					}
////				}
////
////			} else {
////				// same cluster:
////				System.out.println("cluster# " + clusterID + " " + line1);
////
////				String[] rep = line1.split("\\s+");
////				int cl_length = Integer.parseInt(rep[1].substring(0, rep[1].indexOf("aa")));
////				String rep_id = rep[2].substring(1, rep[2].indexOf("..."));
////
////				if (line1.endsWith("*")) { // representative sequence
////					System.out.println("Representative of the cluster");
////
////					System.out.println(rep_id);
////					System.out.println(cl_length);
////
////					cb.setRepresentative(line2); // FIXME: must be the sequence form the original database
////					cb.setSeqid(rep_id);
////					cb.setLength(cl_length);
////
////				} else { // sequence
////					System.out.println("seq id: " + rep_id);
////
////					sb = Sequence.newBuilder();
////					sb.setSeqid(rep_id);
////					sb.setLength(cl_length);
////					sb.setSeq(line2); // FIXME: must be the sequence form the original database
////					sb.setStart(0);
////					sb.setStop(0);
////					sb.setRepStart(0);
////					sb.setRepStop(0);
////					sb.setSimilarity(0);
////					sb.setTaxonomy(""); // FIXME
////
////					cb.addSequence(sb);
////				}
////
////			}
////
////		}
////
////	}
//
//}
