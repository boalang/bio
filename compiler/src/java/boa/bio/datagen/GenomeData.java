package boa.bio.datagen;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import com.google.protobuf.GeneratedMessage;

import boa.types.GFeature.Genome;
import boa.types.GFeature.Sequence;

public class GenomeData {
	public static Configuration conf = new Configuration();
	public static SequenceFile.Reader seqFileReader;
	public static SequenceFile.Writer seqFileWriter;

	public static void main(String[] args) throws IOException {
		openWriter("/Users/hbagheri/Documents/MyGithub/Boa4Genomics/test.seq");
		for(int i=10;i<=140;i++){
			Genome.Builder gb = Genome.newBuilder();
			String key = "!!"+String.valueOf(i)+"!!";
			gb.setRefseq(key);
			gb.setTaxid("tid1");
			Sequence.Builder sb = Sequence.newBuilder();
			//sb.setRefseq(key); from old schema
			sb.setAccession("aid1");
			sb.setHeader("h1");
			gb.addSequence(sb);
			Sequence.Builder sb1 = Sequence.newBuilder();
			//sb1.setRefseq(key); old schema
			sb1.setAccession("aid2");
			sb1.setHeader("h2");
			gb.addSequence(sb1);
			GeneratedMessage gbm = gb.build();
			seqFileWriter.append(new Text(key), 
					new BytesWritable(gbm.toByteArray()));
		}
		
		
		closeWrite();
	}

	public static boolean closeWrite() {
		try {
			seqFileWriter.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static boolean openWriter(String seqPath) {
		FileSystem fileSystem;
		try {
			fileSystem = FileSystem.get(conf);
			seqFileWriter = SequenceFile.createWriter(fileSystem, conf, 
					new Path(seqPath), Text.class,
					BytesWritable.class);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
