
package boa.functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.InvalidProtocolBufferException;

import boa.dsi.DSIProperties;
import boa.evaluator.BoaEvaluator;
import boa.functions.BoaAstIntrinsics.AST_COUNTER;
import boa.types.Ast.ASTRoot;
import boa.types.Diff.ChangedFile;
import boa.types.GFeature;
import boa.types.GFeature.AssemblerRoot;
import boa.types.GFeature.Feature;
import boa.types.GFeature.FeatureRoot;
import boa.types.GFeature.Genome;
import boa.types.GFeature.Sequence;

/**
 * Boa functions for working with ASTs.
 * 
 * @author hbagheri
 */
public class BoaGenomeIntrinsics {
	@SuppressWarnings("rawtypes")
	private static Context context;
	private static MapFile.Reader genemap;
	private static MapFile.Reader assemblermap;
	//private static MapFile.Reader featuremap;
	private static MapFile.Reader sequenceMap;
	private static MapFile.Reader annotationMap;



	

	//@Hamid for Genomics
	//private static final Sequence sequence = Sequence.newBuilder().build();



	/**
	 * Given a accesion code, return the sequence.
	 * 
	 * @param acc the accession code to get a sequence
	 * @return the Sequence
	 */
	@SuppressWarnings("unchecked")
	@FunctionSpec(name = "getSequence", returnType = "Sequence", formalParameters = { "string" })
	public static Sequence getSequence(final String acc) {
		///System.out.println("acc inside getSeq"+acc);
//		final ChangedFile.FileKind kind = f.getKind();
//		if (kind != ChangedFile.FileKind.SOURCE_JAVA_ERROR
//				&& kind != ChangedFile.FileKind.SOURCE_JAVA_JLS2
//				&& kind != ChangedFile.FileKind.SOURCE_JAVA_JLS3
//				&& kind != ChangedFile.FileKind.SOURCE_JAVA_JLS4
//				&& kind != ChangedFile.FileKind.SOURCE_JAVA_JLS8)
//			return emptyAst;

		//context.getCounter(AST_COUNTER.GETS_ATTEMPTED).increment(1);

//		final String rowName = f.getKey() + "!!" + f.getName();

		if (genemap == null)
			openAssemblerMap();

		try {
			final BytesWritable value = new BytesWritable();
			//System.out.println("acc: " + acc);
			if (genemap.get(new Text(acc), value) == null) {
				//System.out.println(acc);
			} else {
				System.out.println("Accession Key Data Found");
				final CodedInputStream _stream = CodedInputStream.newInstance(value.getBytes(), 0, value.getLength());
				_stream.setRecursionLimit(Integer.MAX_VALUE);
				final Sequence root = Sequence.parseFrom(_stream);
				return root;
			}
		} catch (final InvalidProtocolBufferException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final RuntimeException e) {
			e.printStackTrace();
		} catch (final Error e) {
			e.printStackTrace();
		}

		System.err.println("error with gene: " + acc);
		return null;
	}
	
	

	
	
	/**
	 * Given a refseq code, return the AssemblerRoot.
	 * 
	 * @param g the refseq id to get a AssemblerRoot
	 * @return the AssemblerRoot
	 */
	@SuppressWarnings("unchecked")
	@FunctionSpec(name = "getAssembler", returnType = "AssemblerRoot", formalParameters = { "string" })
	public static AssemblerRoot getAssembler(final String g) {
	
		if (assemblermap == null)
			openAssemblerMap();

		try {
			final BytesWritable value = new BytesWritable();
			if (assemblermap.get(new Text(g), value) == null) {
				//System.out.println(acc);
			} else {
				//Assembler record Data Found
				final CodedInputStream _stream = CodedInputStream.newInstance(value.getBytes(), 0, value.getLength());
				_stream.setRecursionLimit(Integer.MAX_VALUE);
				final AssemblerRoot root = AssemblerRoot.parseFrom(_stream);
				return root;
			}
		} catch (final InvalidProtocolBufferException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final RuntimeException e) {
			e.printStackTrace();
		} catch (final Error e) {
			e.printStackTrace();
		}

		System.err.println("error with genome ID: " + g);
		return null;
	}
	

	/**
	 * Given a refseq code, return the FeatureRoot.
	 * 
	 * @param g the refseq id to get a FeatureRoot
	 * @param accessionCode the id of the sequence read
	 * @return the FeatureRoot
	 */
	@SuppressWarnings("unchecked")
	@FunctionSpec(name = "getFeature", returnType = "FeatureRoot", formalParameters = { "string", "string" })
	public static FeatureRoot getFeature(final String g, final String accessionCode) {
	
		if (genemap == null)
			openFeatureMap();

		try {
			final BytesWritable value = new BytesWritable();
			//System.out.println("genome id: " + g + "_" + accessionCode);
			if (genemap.get(new Text(g+"_"+accessionCode), value) == null) {
				//System.out.println(acc);
			} else {
				final CodedInputStream _stream = CodedInputStream.newInstance(value.getBytes(), 0, value.getLength());
				_stream.setRecursionLimit(Integer.MAX_VALUE);
				final FeatureRoot root = FeatureRoot.parseFrom(_stream);
				//System.out.println("##size of  " + g + ":  "+  root.toString().length());
				//System.out.println("##size of  " + g + ":  "+  root.toByteArray().length);

				return root;
			}
		} catch (final InvalidProtocolBufferException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final RuntimeException e) {
			e.printStackTrace();
		} catch (final Error e) {
			e.printStackTrace();
		}

		System.err.println("error with genome ID: " + g + " acc code : " + accessionCode);
		return null;
	}

	
	private static void openAssemblerMap() {
		final Configuration conf = new Configuration();
		try {
			final FileSystem fs = FileSystem.get(conf);
			
			System.out.println(DSIProperties.HADOOP_SEQ_FILE_LOCATION + BoaEvaluator.getDatapath() +"assemblers.seq/");
			
			final Path p = new Path(DSIProperties.HADOOP_SEQ_FILE_LOCATION + BoaEvaluator.getDatapath()+ "assemblers.seq/"); 
			assemblermap = new MapFile.Reader(fs, p.toString(), conf);
			final BytesWritable value = new BytesWritable();
			// FIXME if remove this error map key
			//assemblermap.get(new Text("NZ_JNOU01000054.1"), value);
			//genemap.get(new Text("GCF_000002515.2_ASM251v1"), value);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private static void openFeatureMap() {
		final Configuration conf = new Configuration();
		try {
			final FileSystem fs = FileSystem.get(conf);
			final Path p = new Path(DSIProperties.HADOOP_SEQ_FILE_LOCATION +  BoaEvaluator.getDatapath() +"features.seq/"); 
			genemap = new MapFile.Reader(fs, p.toString(), conf);
			final BytesWritable value = new BytesWritable();
			// FIXME if remove this error map key
			//genemap.get(new Text("GCF_000002515.2_ASM251v1"), value);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void openSequenceMap() {
	    Configuration conf = new Configuration();
	    try {
	      FileSystem fs = FileSystem.get(conf);
	      System.out.println(DSIProperties.HADOOP_SEQ_FILE_LOCATION + BoaEvaluator.getDatapath()  +"sequences.seq/");
	      


	      Path p = new Path(DSIProperties.HADOOP_SEQ_FILE_LOCATION +  BoaEvaluator.getDatapath() + "sequences.seq/");
	      sequenceMap = new MapFile.Reader(fs, p.toString(), conf);
	      BytesWritable localBytesWritable = new BytesWritable();

	    }
	    catch (Exception e)
	    {

	      e.printStackTrace();
	    }
	  }
	
	/**
	 *  @hamid
	 *  @param feature type
	 *  @return true if the feature is gene
	 *  for Genomics domain
	 */	
	@FunctionSpec(name = "isgene", returnType = "bool", formalParameters = { "string" })
	public static boolean isgene(final String ftype){
		try {
			if( ftype.toLowerCase().equals("gene"))
				return true;
		} catch (Exception e) {
			System.out.println("error isgene ");
		}
		
		return false;
	}
	
	/**
	 * @param feature type
	 * @return true if the feature type is exon 
	 */
	@FunctionSpec(name="isexon", returnType="bool", formalParameters={"string"})
	public static boolean isexon(final String ftype){
		
		if (ftype.equals("exon"))
			return true;
		
		return false;
	}
	
	

	
	
	/**
	 *  @hamid
	 *  @param feature type
	 *  @return trun if the feature is gene
	 *  for Genomics domain
	 */
	
	@FunctionSpec(name = "hasoverlap", returnType = "bool", formalParameters = { "string", "Feature" })
	public static boolean hasoverlap(final String acc, final Feature f){

		List<Feature> featureList= getFeatureList(acc);

		for (Feature feature: featureList) {
			
			if( !feature.getAccession().equals(f.getAccession()) 
					&& feature.getFtype().equals(f.getFtype())
					&& feature.getStart()==f.getStart() && feature.getEnd()==f.getEnd() ){
				return true;
			}
			
		}
			
		return false;
	}
	
	private static List<Feature> getFeatureList(String acc) {

		Sequence seq= getSequence(acc);
		
		//FIXME return seq.getFeatureList();
		return null;
	}
	

	
	@FunctionSpec(name="runBash", returnType="string", formalParameters={"string", "string"})
	  public static String runBash(String prog, String refseq)
	  {
	    String bashResults = null;
	    
	    if (prog.equals("larges_gene")) {
	      bashResults = max_gene(refseq);
	    }
	    
	    return bashResults;
	  }
	  
	  private static String max_gene(String refseq)
	  {
	    if (sequenceMap == null) {
	      openSequenceMap();
	    }
	    try {
	      BytesWritable value = new BytesWritable();
	      
	      if (sequenceMap.get(new Text(refseq), value) != null)
//	      {
//
//	        System.out.println("Accession Key Data Found");
//	        _stream = CodedInputStream.newInstance(value.getBytes(), 0, value.getLength());
//	        _stream.setRecursionLimit(Integer.MAX_VALUE);
//	        rootsequence = GFeature.SequenceRoot.parseFrom(_stream);
//	        
//
//	        for (int i = 0; i < rootsequence.getSequenceCount(); i++)
//	        {
//	          if (featureMap == null) {
//	            openFeatureMap();
//	          }
//	          BytesWritable valuefeature = new BytesWritable();
//	          if (featureMap.get(new Text(refseq + "_" + rootsequence.getSequence(i).getAccession()), 
//	            valuefeature) != null)
//	          {
//
//	            CodedInputStream _streamfeature = CodedInputStream.newInstance(valuefeature.getBytes(), 0, 
//	              valuefeature.getLength());
//	            _streamfeature.setRecursionLimit(Integer.MAX_VALUE);
//	            GFeature.FeatureRoot rootfeature = GFeature.FeatureRoot.parseFrom(_streamfeature);
//	            
//	            System.out.println("%%%% start record   " + rootfeature.getFeatureCount());
//	            for (int k = 0; k < rootfeature.getFeatureCount(); k++) {
//	              String gffRecord = rootfeature.getFeature(k).getAccession() + "\t" + 
//	                rootfeature.getFeature(k).getSource() + "\t" + 
//	                rootfeature.getFeature(k).getFtype() + "\t" + rootfeature.getFeature(k).getStart() + 
//	                "\t" + rootfeature.getFeature(k).getEnd() + "\t" + 
//	                rootfeature.getFeature(k).getScore() + "\t" + 
//	                rootfeature.getFeature(k).getStrand() + "\t" + 
//	                rootfeature.getFeature(k).getPhase() + "\t" + 
//	                rootfeature.getFeature(k).getSource();
//	              
//
//	              strGFF = strGFF + "\n" + gffRecord;
//	            }
//	          }
//	        }
//	      }
	      




//	      _stream = null;rootsequence = null; label496: try { out = new PrintWriter("/Users/hbagheri/Downloads/testRaw.gff");
//	      } finally { PrintWriter out;
//	        _stream = rootsequence; break label496; if (_stream != rootsequence) _stream.addSuppressed(rootsequence);
//	      }
//	      String cmdResults = null;
//	      









//	      try
//	      {
//	        ProcessBuilder pb = 
//	        
//	          new ProcessBuilder(new String[] { "awk", "BEGIN{max=0}{if ($5-$4>0+max) max=$5-$4} END{print $1 \"  \" $4 \"  \" $5}", "/Users/hbagheri/Downloads/testRaw.gff" });
//	        
//	        Process p = pb.start();
//	        
//	        BufferedReader br = new BufferedReader(
//	        
//	          new InputStreamReader(
//	          
//	          p.getInputStream()));
//	        
//	        String line;
//	        
//	        while ((line = br.readLine()) != null) {
//	          String line;
//	          System.out.println(line);
//	          cmdResults = cmdResults + line;
//	        }
//	        
//	        return cmdResults;
//	      }
//	      catch (Exception ex)
//	      {
//	        System.out.println(ex);
//	      }
//	      













	      System.err.println("#runBash  error with gene: " + refseq);
	    }
	    catch (InvalidProtocolBufferException e)
	    {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } catch (RuntimeException e) {
	      e.printStackTrace();
	    } catch (Error e) {
	      e.printStackTrace();
	    }
	    

	    return null;
	  }
	  
	  public static String execCmd(String cmd) throws IOException {
	    Process proc = Runtime.getRuntime().exec(cmd);
	    InputStream is = proc.getInputStream();
	    Scanner s = new Scanner(is).useDelimiter("\\A");
	    String val = "";
	    if (s.hasNext()) {
	      val = s.next();
	    } else {
	      val = "";
	    }
	    return val;
	  }
	  

	
	}
