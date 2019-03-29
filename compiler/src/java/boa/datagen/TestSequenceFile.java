package boa.datagen;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import com.google.protobuf.CodedInputStream;

import boa.dsi.DSIProperties;
import boa.types.Ast.ASTRoot;
import boa.types.GFeature.AssemblerRoot;
import boa.types.GFeature.FeatureRoot;

public class TestSequenceFile {

	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fileSystem = FileSystem.get(conf);
//		
//		SequenceFile.Writer w = SequenceFile.createWriter(fileSystem, conf, new Path("seq"), Text.class, BytesWritable.class);
//		for (int i = 0; i < 1; i++) {
//			ASTRoot.Builder ast = ASTRoot.newBuilder();
//			ast.addImports("a.b.C");
//			w.append(new Text(i + ""), new BytesWritable(ast.build().toByteArray()));
//			System.out.println("Parse before writing to sequence file: " + ASTRoot.parseFrom(ast.build().toByteArray()).getImportsList());
//		}
//		w.close();
		
		String seqFile = args[0];
		
		Text key = new Text();
		BytesWritable val = new BytesWritable();
		SequenceFile.Reader seqFile_Reader= new SequenceFile.Reader(fileSystem,
				new Path(seqFile),conf);

////		SequenceFile.Reader gene= new SequenceFile.Reader(fileSystem,
//				new Path("/home/seq_files/annotations.seq/data"), 
//				conf);
		
		
//		SequenceFile.Reader r = new SequenceFile.Reader(fileSystem, new Path(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/" + DSIProperties.HADOOP_SEQ_FILE_NAME), conf);
//		SequenceFile.Reader ast = new SequenceFile.Reader(fileSystem, new Path(DSIProperties.HADOOP_SEQ_FILE_LOCATION + "/" + "data"), conf);
		while (seqFile_Reader.next(key, val)) {
			
//			if(!key.toString().equals("NZ_JNOU01000054.1"))
//			{
//				continue;
//			}
			
 
//			if (key.toString().equals("YP_195122.1")) {
			//if (key.toString().startsWith("PIR")) {

				
				
				System.out.println("key:" + key.toString());
				byte[] bytes = val.getBytes();
				
				//System.out.println(boa.types.Nr.Cluster.parseFrom(CodedInputStream.newInstance(bytes, 0, val.getLength())));
				//System.out.println(boa.types.Nr2.Sequence.parseFrom(CodedInputStream.newInstance(bytes, 0, val.getLength())));

				
//			}
			
			
			boa.types.GFeature.Genome genome = boa.types.GFeature.Genome.parseFrom(CodedInputStream.newInstance(bytes, 0, val.getLength()));
			System.out.println(genome);
			
			//Parse assembler data
			//AssemblerRoot asmr=boa.types.GFeature.AssemblerRoot.parseFrom(CodedInputStream.newInstance(bytes, 0, val.getLength()));
			//System.out.println(boa.types.GFeature.AssemblerRoot.parseFrom(CodedInputStream.newInstance(bytes, 0, val.getLength())));

			//Parse Fastq  dataset
			//boa.types.SRA.Fastq fastq=boa.types.SRA.Fastq.parseFrom(CodedInputStream.newInstance(bytes, 0, val.getLength()));
			//System.out.println(fastq);

				
			//Parse feature data
			//FeatureRoot featureData=boa.types.GFeature.FeatureRoot.parseFrom(CodedInputStream.newInstance(bytes, 0, val.getLength()));
		    //System.out.println(featureData);
//			System.out.println(" Length: "+  bytes.length/1000000 + " MB");

			
		}
//		while (ast.next(key, val)) {
//			byte[] bytes = val.getBytes();
//			System.out.println(ASTRoot.parseFrom(CodedInputStream.newInstance(bytes, 0, val.getLength())));
//		}
//		r.close();
		
		
		System.out.println("Parsing done successfully! ");
	}

	
}
