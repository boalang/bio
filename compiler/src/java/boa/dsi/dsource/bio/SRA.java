package boa.dsi.dsource.bio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.antlr.runtime.misc.FastQueue;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import com.aol.cyclops.data.async.Queue;
import com.google.protobuf.GeneratedMessage;

import boa.dsi.DSIProperties;
import boa.dsi.dsource.AbstractSource;
import boa.dsi.storage.AbstractStorage;


public class SRA extends AbstractStorage {
	protected static Configuration conf;
	SequenceFile.Reader seqFileReader;

	protected static SequenceFile.Writer sra_seqFileWriter;

	public SRA(String location, AbstractSource parserSource) {
		super(location, parserSource);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		conf = new Configuration();

		
		String[] names = { "@NCC-1701-D", "@NCC-1701-A" };

		Set<String> nameset = new HashSet<String>();
		nameset.addAll(Arrays.asList(names));

		String path = args[0];

		System.out.println("Inside store Hadoop sequence File Path " + DSIProperties.HADOOP_SEQ_FILE_LOCATION
				+ DSIProperties.HADOOP_SRA_FILE_NAME);

		openRefSeqWriter(DSIProperties.HADOOP_SEQ_FILE_LOCATION + DSIProperties.HADOOP_SRA_FILE_NAME);

		List<File> Fastq_fileList = new ArrayList<File>();

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
			Fastq_fileList.addAll(Arrays.asList(getFastq(folder.getPath())));
		}

		for (File file : Fastq_fileList) {

			System.out.println("File Name: " + file.getName());

			if (file.isFile()) {

				// parse FASTQ file
				// TODO: use biojava for (Fastq fastq : fastqReader.read(new
				// File(“sanger.fastq”))) {

				boa.types.SRA.Fastq.Builder fb = null;
				
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(file.getAbsolutePath()));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				long nLine = -1L;
				String line;
				try {
					while ((line = br.readLine()) != null) {
						nLine++;
//						if (nLine % 4 != 0 || !nameset.contains(line))
//							continue;
						System.out.println(line);
						
						fb =  new boa.types.SRA.Fastq.Builder();
						
						String line1=line; //id
						String line2 = br.readLine();//seq
						String line3 = br.readLine();//desc
						String line4 = br.readLine();//quality 
						
						fb.setId(line1.substring(line1.indexOf(" ")).trim());
						String[] words = line1.split(" ");
						String key = words[0];
						
						fb.setSeq(line2);
						fb.setDesc(line3.substring(line3.indexOf(" ")).trim());
						fb.setQuality(line4);
						

						nLine += 3;
						
						try {
							sra_seqFileWriter.append(new Text(String.valueOf(key)),
									new BytesWritable(fb.build().toByteArray()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		closeRefSeqWrite();

	}

	protected static boolean openRefSeqWriter(String seqPath) {
		FileSystem fileSystem;
		try {
			fileSystem = FileSystem.get(conf);
			sra_seqFileWriter = SequenceFile.createWriter(fileSystem, conf,
					new Path(seqPath + DSIProperties.HADOOP_SEQ_FILE_NAME), Text.class, BytesWritable.class);

			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	protected static boolean closeRefSeqWrite() {
		try {
			
			sra_seqFileWriter.close();
			
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	

	private static File[] getFastq(String path) {
		File dir = new File(path);
		File[] gffFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".fastq");
			}
		});
		return gffFiles;

	}

	@Override
	public List<GeneratedMessage> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAvailable(String source) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void store(List<GeneratedMessage> dataInstance) {
		// TODO Auto-generated method stub

	}

	@Override
	public void store(Queue<GeneratedMessage> queue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void store(String path) {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeAt(String location, GeneratedMessage dataInstance) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDataLocation() {
		// TODO Auto-generated method stub
		return null;
	}

}
