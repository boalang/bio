
package boa.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.InvalidProtocolBufferException;

import boa.dsi.DSIProperties;
import boa.dsi.storage.AbstractStorage;
import boa.evaluator.BoaEvaluator;
import boa.functions.BoaAstIntrinsics.AST_COUNTER;
import boa.types.Ast.ASTRoot;
import boa.types.Diff.ChangedFile;

/**
 * Boa functions for working with ASTs.
 * 
 * @author hbagheri
 */
public class BoaNrIntrinsics {
	@SuppressWarnings("rawtypes")
	private static Context context;
	private static MapFile.Reader genemap;
	private static MapFile.Reader assemblermap;
	// private static MapFile.Reader featuremap;
	private static MapFile.Reader sequenceMap;
	private static MapFile.Reader annotationMap;

	/**
	 * Given a seqid , return the Annotations.
	 * 
	 * @param s the sequence id to get Annotations
	 * @return the String
	 */
//	@SuppressWarnings("unchecked")
//	@FunctionSpec(name = "getAnnotations", returnType = "string", formalParameters = { "string" })
//	
//		if (annotationMap == null)
//			openAnnotationsMap();
//
//		String key = s.toUpperCase();
//		String modified_Key=null;
//		
//		if (key.indexOf("||")> -1) {
//			key= key.replace("||", "00");
//			//System.out.println("modified Key: " + key);
//		}
//		
//		try {
//			final BytesWritable value = new BytesWritable();
//			if (annotationMap.get(new Text(key), value) == null) {
//				//System.out.println(acc);
//			} else {
//				//Annotation record Data Found
//				final CodedInputStream _stream = CodedInputStream.newInstance(value.getBytes(), 0, value.getLength());
//				_stream.setRecursionLimit(Integer.MAX_VALUE);
//				final boa.types.Nr.Annotation root = Annotation.parseFrom(_stream);
//				String defline= root.getDefline();
//				
//				String[] ann= defline.split("");
//				String[] words = ann[0].split(" ");
//				
//				//String stat=getStat(defline,  2);
//				
//				return defline;
//			}
//		} catch (final InvalidProtocolBufferException e) {
//			e.printStackTrace();
//		} catch (final IOException e) {
//			e.printStackTrace();
//		} catch (final RuntimeException e) {
//			e.printStackTrace();
//		} catch (final Error e) {
//			e.printStackTrace();
//		}
//
//		System.err.println("error with sesquence ID: " + s);
//		return null;
//	}

	/**
	 * Given a seqid , return the taxonomy list.
	 * 
	 * @param s the sequence id to get Annotations
	 * @return the String
	 */
	@SuppressWarnings("unchecked")
	@FunctionSpec(name = "getTaxonomy", returnType = "string", formalParameters = { "string" })
	public static String getTaxonomy(final String defline) {
		int top = 1;
		String[] ann = defline.split("");
		String keyID = null;
		String protein = null;
		String taxonomy = null;
		String taxa_list = "";
		
		//TODO: just test remove it
		taxa_list = "test test test test test this is test test";

//		Map<String, Integer> taxa_map = new HashMap<String, Integer>();
//
//		for (int i = 0; i < ann.length; i++) {
//			String[] words = ann[i].split(" ");
//			keyID = words[0];
//			if (ann[i].indexOf("[") > 0) {
//				protein = ann[i].substring(ann[i].indexOf(" "), ann[i].indexOf("["));
//			} else {
//				protein = ann[i].substring(ann[i].indexOf(" "));
//			}
//			taxonomy = ann[i].substring(ann[i].indexOf("[") + 1, ann[i].length() - 1);
//
//			if (taxa_map.get(taxonomy) != null) {
//				taxa_map.put(taxonomy, taxa_map.get(taxonomy) + 1);
//			} else {
//				taxa_map.put(taxonomy, 1);
//			}
//		}
//		
//		// list of taxonomy 
//		for (String key : taxa_map.keySet()) {
//			taxa_list += key + "=" + taxa_map.get(key) + " |";
//
//		}

		return taxa_list;
	}

	/**
	 * Given a seqid , return the Annotations.
	 * 
	 * @param s the sequence id to get Annotations
	 * @return the String
	 */
	@SuppressWarnings("unchecked")
	@FunctionSpec(name = "getDominant", returnType = "string", formalParameters = { "string", "string" })
	public static String getDominant(final String s, final String cleanStr) {
		int top = 1;

		String defline = s;

		// Clean the defline before sending it to getstat()
		String[] words = cleanStr.split(";");
		for (int i = 0; i < words.length; i++) {
			defline = defline.replaceAll(words[i], "");
			if (words[i].startsWith("X")) {
				// TODO: remove regular expression
			}
		}

		try {
			String stat = getStat(defline, top);
			return stat;
		} catch (Exception e) {
			// TODO: handle exception
			return "##ERROR Annotation";
		}
		

		

	}

	/**
	 * Given a seqid , return the Annotations.
	 * 
	 * @param s the sequence id to get Annotations
	 * @return the String
	 */
	@SuppressWarnings("unchecked")
	@FunctionSpec(name = "getFunctions", returnType = "string", formalParameters = { "string" })
	public static String getFunctions(final String defline) {

		String[] ann = defline.split("");
		String keyID = null;
		String protein = null;
		String org_name = null;

		Map<String, Integer> stat = new HashMap<String, Integer>();

		for (int i = 0; i < ann.length; i++) {
			String[] words = ann[i].split(" ");
			keyID = words[0];
			if (ann[i].indexOf("[") > 0) {
				protein = ann[i].substring(ann[i].indexOf(" "), ann[i].indexOf("["));
			} else {
				protein = ann[i].substring(ann[i].indexOf(" "));
			}
			org_name = ann[i].substring(ann[i].indexOf("[") + 1, ann[i].length() - 1);

			if (stat.get(protein) != null) {
				stat.put(protein, stat.get(protein) + 1);
			} else {
				stat.put(protein, 1);
			}
		}

		String ann_stat = "";

		for (String key : stat.keySet()) {
			ann_stat += key + " | ";

		}

		return ann_stat;

	}

	/**
	 * Given a seqid , return the Annotations.
	 * 
	 * @param s the sequence id to get Annotations
	 * @return the String
	 */
	@SuppressWarnings("unchecked")
	@FunctionSpec(name = "getCounts", returnType = "int", formalParameters = { "string" })
	public static int getCounts(final String defline) {

		String[] ann = defline.split("");
		String keyID = null;
		String protein = null;
		String org_name = null;

		Map<String, Integer> stat = new HashMap<String, Integer>();

		for (int i = 0; i < ann.length; i++) {
			String[] words = ann[i].split(" ");
			keyID = words[0];
			if (ann[i].indexOf("[") > 0) {
				protein = ann[i].substring(ann[i].indexOf(" "), ann[i].indexOf("["));
			} else {
				protein = ann[i].substring(ann[i].indexOf(" "));
			}
			org_name = ann[i].substring(ann[i].indexOf("[") + 1, ann[i].length() - 1);

			if (stat.get(protein) != null) {
				stat.put(protein, stat.get(protein) + 1);
			} else {
				stat.put(protein, 1);
			}
		}

		return stat.size();

	}

	private static String getStat(String defline, int top) {

		String[] ann = defline.split("");
		String keyID = null;
		String protein = null;
		String org_name = null;

		Map<String, Integer> stat = new HashMap<String, Integer>();

		for (int i = 0; i < ann.length; i++) {

			try {
				String[] words = ann[i].split(" ");
				keyID = words[0];
//				if (keyID.equals("4JT8_A"))
//					System.out.println(ann);
				if (ann[i].indexOf("[") > 0) {
					// System.out.println(ann[i]);
					protein = ann[i].substring(ann[i].indexOf(" "), ann[i].lastIndexOf("["));
					if (ann[i].lastIndexOf("]") > 0)
						org_name = ann[i].substring(ann[i].lastIndexOf("[") + 1, ann[i].length() - 1);
				} else {
					// System.out.println(ann[i]);
					if (ann[i].indexOf(" ") > 0) {
						protein = ann[i].substring(ann[i].indexOf(" "));
					} else {
						protein = ann[i];
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(ann[i]);
				System.out.println(e);
			}

			if (stat.get(protein) != null) {
				stat.put(protein, stat.get(protein) + 1);
			} else {
				stat.put(protein, 1);
			}

		}

		// sort the hashmap
		Map<String, Integer> sorted_stat = sortByComparator(stat, false);

		String ann_stat = "";

		for (String key : stat.keySet()) {
			ann_stat += key + " " + stat.get(key);

		}

		// System.out.println("before: " + ann_stat);

		ann_stat = "";
		int i = 0;

		for (String key : sorted_stat.keySet()) {
			// ann_stat += key + "=" + sorted_stat.get(key) ;
			ann_stat += key;
			i += 1;
			if (i == top || sorted_stat.size() < top)
				break;
			else
				ann_stat += " | ";
		}

		// System.out.println("after: " + ann_stat);

		sorted_stat=null;
		stat=null;
		//System.gc();
		
		return ann_stat;

	}

	private static String get_taxonomy(String defline) {

		String[] ann = defline.split("");
		String keyID = null;
		String protein = null;
		String taxonomy = null;

		Map<String, Integer> taxa_map = new HashMap<String, Integer>();

		for (int i = 0; i < ann.length; i++) {
			String[] words = ann[i].split(" ");
			keyID = words[0];
			if (ann[i].indexOf("[") > 0) {
				protein = ann[i].substring(ann[i].indexOf(" "), ann[i].indexOf("["));
			} else {
				protein = ann[i].substring(ann[i].indexOf(" "));
			}
			taxonomy = ann[i].substring(ann[i].indexOf("[") + 1, ann[i].length() - 1);

			if (taxa_map.get(taxonomy) != null) {
				taxa_map.put(taxonomy, taxa_map.get(taxonomy) + 1);
			} else {
				taxa_map.put(taxonomy, 1);
			}
		}

		String taxa_list = "";

		for (String key : taxa_map.keySet()) {
			taxa_list += key + "=" + taxa_map.get(key) + " |";

		}

		return taxa_list;

	}

	private static int get_counts(String defline) {

		String[] ann = defline.split("");
		String keyID = null;
		String protein = null;
		String org_name = null;

		Map<String, Integer> stat = new HashMap<String, Integer>();

		for (int i = 0; i < ann.length; i++) {
			String[] words = ann[i].split(" ");
			keyID = words[0];
			if (ann[i].indexOf("[") > 0) {
				protein = ann[i].substring(ann[i].indexOf(" "), ann[i].indexOf("["));
			} else {
				protein = ann[i].substring(ann[i].indexOf(" "));
			}
			org_name = ann[i].substring(ann[i].indexOf("[") + 1, ann[i].length() - 1);

			if (stat.get(protein) != null) {
				stat.put(protein, stat.get(protein) + 1);
			} else {
				stat.put(protein, 1);
			}
		}

		return stat.size();

	}

	private static String get_functions(String defline) {

		String[] ann = defline.split("");
		String keyID = null;
		String protein = null;
		String org_name = null;

		Map<String, Integer> stat = new HashMap<String, Integer>();

		for (int i = 0; i < ann.length; i++) {
			String[] words = ann[i].split(" ");
			keyID = words[0];
			if (ann[i].indexOf("[") > 0) {
				protein = ann[i].substring(ann[i].indexOf(" "), ann[i].indexOf("["));
			} else {
				protein = ann[i].substring(ann[i].indexOf(" "));
			}
			org_name = ann[i].substring(ann[i].indexOf("[") + 1, ann[i].length() - 1);

			if (stat.get(protein) != null) {
				stat.put(protein, stat.get(protein) + 1);
			} else {
				stat.put(protein, 1);
			}
		}

		String ann_stat = "";

		for (String key : stat.keySet()) {
			ann_stat += key + " | ";

		}

		return ann_stat;
	}

	private static Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public static void printMap(Map<String, Integer> map) {
		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}
	}

//	private static void openAssemblerMap() {
//		final Configuration conf = new Configuration();
//		try {
//			final FileSystem fs = FileSystem.get(conf);
//			
//			System.out.println(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "assemblers.seq/");
//			
//			final Path p = new Path(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "assemblers.seq/"); 
//			assemblermap = new MapFile.Reader(fs, p.toString(), conf);
//			final BytesWritable value = new BytesWritable();
//			// FIXME if remove this error map key
//			//assemblermap.get(new Text("NZ_JNOU01000054.1"), value);
//			//genemap.get(new Text("GCF_000002515.2_ASM251v1"), value);
//
//		} catch (final Exception e) {
//			e.printStackTrace();
//		}
//	}

	private static void openAnnotationsMap() {
		final Configuration conf = new Configuration();
		try {
			final FileSystem fs = FileSystem.get(conf);

			System.out.println(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "annotations.seq/");

			final Path p = new Path(
					DSIProperties.HADOOP_SEQ_FILE_LOCATION + BoaEvaluator.getDatapath() + "annotations.seq/");
			annotationMap = new MapFile.Reader(fs, p.toString(), conf);
			final BytesWritable value = new BytesWritable();
			// FIXME if remove this error map key
			// assemblermap.get(new Text("NZ_JNOU01000054.1"), value);
			// genemap.get(new Text("GCF_000002515.2_ASM251v1"), value);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static void openFeatureMap() {
		final Configuration conf = new Configuration();
		try {
			final FileSystem fs = FileSystem.get(conf);
			final Path p = new Path(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "features.seq/");
			genemap = new MapFile.Reader(fs, p.toString(), conf);
			final BytesWritable value = new BytesWritable();
			// FIXME if remove this error map key
			// genemap.get(new Text("GCF_000002515.2_ASM251v1"), value);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private static void openSequenceMap() {
		Configuration conf = new Configuration();
		try {
			FileSystem fs = FileSystem.get(conf);
			System.out.println("/Users/hbagheri/Documents/MyGithub/Boa4Genomics/assemblers.seq/");
			System.out.println(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "sequences.seq/");

			Path p = new Path(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "sequences.seq/");
			sequenceMap = new MapFile.Reader(fs, p.toString(), conf);
			BytesWritable localBytesWritable = new BytesWritable();

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	

//	/**
//	 *  @hamid
//	 *  @param feature type
//	 *  @return trun if the feature is gene
//	 *  for Genomics domain
//	 */
//	
//	@FunctionSpec(name = "hasoverlap", returnType = "bool", formalParameters = { "string", "Feature" })
//	public static boolean hasoverlap(final String acc, final Feature f){
//
//		List<Feature> featureList= getFeatureList(acc);
//
//		for (Feature feature: featureList) {
//			
//			if( !feature.getAccession().equals(f.getAccession()) 
//					&& feature.getFtype().equals(f.getFtype())
//					&& feature.getStart()==f.getStart() && feature.getEnd()==f.getEnd() ){
//				return true;
//			}
//			
//		}
//			
//		return false;
//	}

	@FunctionSpec(name = "readFile", returnType = "string", formalParameters = { "string" })
	public static String readFile(String addr) {
		String fileContent = null;

		Configuration conf = new Configuration();
		FileSystem fs =null;
		FSDataInputStream inputStream =null;
	
		// method1: we can run like any bash command and just print the output
//		String cmd =   addr;
//		String outputCMD = null;
//
//		try {
//			outputCMD= execCmd(cmd);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		fileContent= outputCMD;
		
		// method 2 : open the file with file system
		Path path = new Path(addr);
		try {
			fs = FileSystem.get(path.toUri(), conf);
			
			inputStream = fs.open(path);
//		    System.out.println(inputStream.toString());
//		    int bytesRead = 0;
//		    byte buffer[] = new byte[256];
//
//
//		    while ((bytesRead = inputStream.read(buffer)) > 0) {
//		    	System.out.println(buffer);
//		    }
			
			//IOUtils.copyBytes(inputStream, System.out, 4096, false);
			//String theString = IOUtils.toString(inputStream, "UTF-8");
			//String text = new TextOf(inputStream).asString();


			StringWriter writer = new StringWriter();
			IOUtils.copy(inputStream, writer, "UTF-8");
			fileContent = writer.toString();
			
			if (inputStream !=null)
			 inputStream.close();
			
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			try {
//				IOUtils.closeQuietly(inputStream);;
//		        if(inputStream!=null)
//		        	 inputStream.close();
//		        
//		        if (fs!=null)
//		        	fs.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
		
		
		//fileContent = "1NIR,1NIW,1A8D,1A8S";

		return fileContent;
	}
	
	

}
