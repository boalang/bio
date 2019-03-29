package boa.dsi.dsource.bio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.biojava.nbio.genome.parsers.gff.FeatureI;
import org.biojava.nbio.genome.parsers.gff.FeatureList;
import org.biojava.nbio.genome.parsers.gff.GFF3Reader;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;

import boa.dsi.dsource.AbstractSource;
import boa.dsi.dsource.gff.GFF;
import boa.dsi.dsource.gff.Seq;
import boa.dsi.dsource.gff.SeqFeature;
import boa.types.GFeature.Assembler;
import boa.types.GFeature.AssemblerRoot;
import boa.types.GFeature.Attribute;
import boa.types.GFeature.Feature;
import boa.types.GFeature.FeatureRoot;
import boa.types.GFeature.Genome;
import boa.types.GFeature.Sequence;

public class Genomics extends AbstractSource {

	private GFF reader;

	public Genomics(ArrayList<String> source) {
		super(source);

	}

	public static void main(String[] args) {

		List<String> sequenceFields = new ArrayList<String>();
		List<String> featureFields = new ArrayList<String>();

		// parseGFF("/Users/hbagheri/Documents/bio/protozoa/gff/GCF_000002415.2_ASM241v2_genomic.gff");

		// get_taxID(new
		// File("/Users/hbagheri/Documents/bio/protozoa/gff/GCF_000002415.2_ASM241v2_genomic.gff"));

		// TODO: change path to source
		// GFF reader = new GFF("/Users/hbagheri/Documents/bio/testncbi");
		// GenomicsData genomics = new GenomicsData();

		// //TODO both fields should be sent?
		// genomics=reader.getData();
		// List<Seq> sequenceData =genomics.getSequences();
		// List<SeqFeature> featureData = genomics.getFeatures();

		// getData();

	}

	@Override
	public GeneratedMessage parseFrom(CodedInputStream stream) throws IOException {
		return Sequence.parseFrom(stream);
	}

	@Override
	public boolean isReadable(String source) {
		return reader.isReadable(source);

	}

	@Override
	public List<GeneratedMessage> getData() {

		// FIXME Demo purpose

		return this.buildData();
		// return this.buildData();

	}

	private List<GeneratedMessage> buildData() {

		List<GeneratedMessage> genome_data = new ArrayList<GeneratedMessage>();

		List<File> sources = new ArrayList<File>();

		// List of folders corresponds to the NCBI refseq
		String path="/Users/hbagheri/Documents/NCBI/Data/protozoa";
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
			sources.addAll(Arrays.asList(getGFF(folder.getPath())));
		}
		// sources.addAll(Arrays.asList(directories));

		for (File file : sources) {
			if (file.isFile()) {
				FeatureList fList;
				try {
					fList = GFF3Reader.read(file.getPath());

					String taxID = get_taxID(new File(file.getPath()));

					String fileName = FilenameUtils.removeExtension(file.getName());
					String refseq = fileName.substring(0, fileName.indexOf("genomic") - 1);
					String assembly_stats = file.getPath().substring(0, file.getPath().indexOf("genomic"))
							+ "assembly_stats.txt";
					List<String> assemblers = get_assemblers(new File(assembly_stats));

					Genome.Builder gb = Genome.newBuilder();
					gb.setTaxid(taxID);
					gb.setRefseq(refseq);

					String currKey = null;
					Sequence.Builder sb = null;
					FeatureRoot.Builder frb = FeatureRoot.newBuilder();
					frb.setRefseq(refseq);

					for (FeatureI feature : fList) {

						if (currKey == null) {
							// new sequence region
							sb = Sequence.newBuilder();

							currKey = feature.seqname();
							sb.setAccession(feature.seqname());
							sb.setHeader(fileName);
							 
						} else if (!currKey.equals(feature.seqname())) {
							// sb.setFeatureRoot(frb.build());
							gb.addSequence(sb.build());
							// new sequence region
							sb = Sequence.newBuilder();

							currKey = feature.seqname();
							sb.setAccession(feature.seqname());
							sb.setHeader(fileName);
							 
						}

						// the same sequence region
						// it is a feature just add to the new FeatureRoot
						Feature.Builder fb = Feature.newBuilder();
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

					// Bind all assemblers to the current genome file
//					AssemblerRoot.Builder arb = AssemblerRoot.newBuilder();
//					arb.setRefseq(refseq);
//					arb.setContigCount(contig_count);
//					arb.setContigN50(contig_N50);
//					arb.setScaffoldCount(scaffold_count);
//					arb.setScaffoldN50(scaffold_N50);
//					arb.setTotalGapLength(total_gap_length);
//					arb.setTotalLength(total_length);
//
//					Assembler.Builder asb = null;
//					for (String assembler : assemblers) {
//						asb = Assembler.newBuilder();
//						asb.setRefseq(refseq);
//						String[] data = assembler.split("\\s+");
//						asb.setName(data[1]);
//						asb.setDesc(assembler);
//
//						arb.addAssembler(asb.build());
//
//					}

					// No need to save this as a gb because assemblers.seq will
					// be another seq file
					// gb.setAssemblerRoot(arb.build());

					// add genome builder and feature builder to data message
					genome_data.add(gb.build());
					//feature_data.add(frb.build());
					//assembler_data.add(arb.build());
					// // write Key,Value pair to the *.seq files
					// try {
					// // FIXME Avoid overflowing the int too early by casting
					// // to a long.
					// // long newSize = Math.min(Integer.MAX_VALUE, (3L *
					// // gb.build().toByteArray().length) / 2L);
					// // BytesWritable dataValue = new BytesWritable();
					// // dataValue.setCapacity((int) newSize);
					// // dataValue.setSize((int) newSize);
					// // dataValue.set(new BytesWritable(
					// // gb.build().toByteArray()));
					//
					// GeneratedMessage gm=gb.build();
					// String key=
					// Genome.parseFrom(gm.toByteArray()).getRefseq();
					//
					// this.project_seqFileWriter.append(key,
					// new BytesWritable(gm.toByteArray()));
					// } catch (IOException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }

					


				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		
		return (genome_data);
	}

	private List<GeneratedMessage> buildData_Demo() {

		List<GeneratedMessage> data = new ArrayList<GeneratedMessage>();

		// String path = "/Users/hbagheri/Documents/bio/protozoa/gff";
		String path = "/Users/hbagheri/Documents/NCBI/testncbi2";
		File dir = new File(path);
		File[] gffFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".gff");
			}
		});

		for (int i = 0; i < gffFiles.length; i++) {
			if (gffFiles[i].isFile()) {
				FeatureList fList;
				try {
					fList = GFF3Reader.read(gffFiles[i].getPath());
					String taxID = get_taxID(new File(gffFiles[i].getPath()));
					String fileName = FilenameUtils.removeExtension(gffFiles[i].getName());
					String outFile = gffFiles[i].getParentFile() + "/" + fileName + ".txt";
					PrintWriter pw = new PrintWriter(new FileWriter(outFile));

					// PrintStream ps= new PrintStream(new File(outFile));
					// System.setOut(ps);

					// System.out.println(" tax ID: " + taxID);

					Set<String> accessionSet = new HashSet<String>();

					for (FeatureI feature : fList) {
						accessionSet.add(feature.seqname());
					}
					for (String seq : accessionSet) {
						Sequence.Builder sb = Sequence.newBuilder();
						sb.setAccession(seq);
						sb.setHeader(fileName);
						 
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
									fb.addAttribute(ab);
								}

								// FIXME : after schema changes
								// sb.addFeature(fb);

							}

						}

						// FIXME: write to the disk
						// EOR ?
						pw.printf("%s /n", sb.build().toString());
						// System.out.println(sb.build());
						data.add(sb.build());

					}

					pw.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return data;

	}

	private List<GeneratedMessage> buildData1() {

		// List<String> sequenceFields = new ArrayList<String>();
		// List<String> featureFields = new ArrayList<String>();

		// TODO: change path to source
		// this.reader= new GFF(path);
		// GenomicsData genomics= new GenomicsData();
		// genomics=this.reader.getData();

		List<GeneratedMessage> data = new ArrayList<GeneratedMessage>();

		// for each fna file, go through all features
		// first parse_fna and then parse_gff

		// (dir, name) -> {
		// return name.toLowerCase().endsWith(".gff");
		// }

		String path = "/Users/hbagheri/Documents/bio/testncbi";
		File dir = new File(path);
		File[] fnaFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".fna");
			}
		});

		for (int i = 0; i < fnaFiles.length; i++) {

			if (fnaFiles[i].isFile()) {

				// sequene List and then add to the queue message
				List<Seq> sequenceList = get_fna(fnaFiles[i]);
				String gffFile = FilenameUtils.removeExtension(fnaFiles[i].getPath()) + ".gff";
				List<SeqFeature> featureList = get_gff(new File(gffFile));

				for (Seq sequence : sequenceList) {

					Sequence.Builder sb = Sequence.newBuilder();

					String seqid = sequence.getAccessionNum();
					sb.setAccession(sequence.getAccessionNum());
					sb.setHeader(sequence.getHeader());

					Feature.Builder fb = null;
					for (SeqFeature seqFeature : featureList) {

						fb = Feature.newBuilder();
						// check if these features belongs to the sequence; have
						// the same accession code
						// System.out.println( " compare ...." +
						// seqFeature.getAccessionNum() + " --" + seqid);
						if (seqFeature.getAccessionNum().equals(seqid)) {
							fb.setAccession(seqFeature.getAccessionNum());
							fb.setSeqid(seqFeature.getSeqid());
							fb.setSource(seqFeature.getSource());
							fb.setFtype(seqFeature.getfType());
							fb.setStart(seqFeature.getStart());
							fb.setEnd(seqFeature.getEnd());
							fb.setScore(seqFeature.getScore());
							fb.setStrand(seqFeature.getStrand());
							fb.setPhase(seqFeature.getPhase());

							// FIXME: after schema changes
							// sb.addFeature(fb.build());

						}

					}

					data.add(sb.build());
				}

			}
		}

		return data;

	}

	public static void parseGFF(String strFile) {
		try {
			FeatureList f = GFF3Reader.read(strFile);

			get_taxID(new File(strFile));

			// seqName= f.selectByGroup("seqname");

			Set<String> accSet = new HashSet<String>();

			f.attributeValues("seqname");
			for (FeatureI feature : f) {
				System.out.println("seqName: " + feature.seqname());
				System.out.println(" feature id : " + feature.getAttribute("ID"));
				// System.out.println( "location: " + feature.location());

				accSet.add(feature.seqname());

				String[] rec = feature.toString().split("\t");

				// System.out.println( rec[0]+ "__" + rec[1]+ "__"+ rec[2]+ "__"
				// + rec[3]+ "__" + rec[4]+ "__"+ rec[5]+ "__");

				System.out.println(feature);
				for (Map.Entry<String, String> entry : feature.getAttributes().entrySet()) {
					System.out.println("		tag: " + entry.getKey() + " value: " + entry.getValue());
				}
			}

			System.out.println("SeqName Size: " + accSet.size());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private List<GeneratedMessage> buildGFFData() {

		List<GeneratedMessage> data = new ArrayList<GeneratedMessage>();

		String path = "/Users/hbagheri/Documents/bio/protozoa/gff";
		File dir = new File(path);
		File[] gffFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".gff");
			}
		});

		for (int i = 0; i < gffFiles.length; i++) {

			if (gffFiles[i].isFile()) {

				Set<String> accSet = new HashSet<String>();
				accSet = get_Accessions(gffFiles[i]);

				String gffFile = FilenameUtils.removeExtension(gffFiles[i].getPath()) + ".gff";
				List<SeqFeature> featureList = get_gff(new File(gffFile));

				System.out.println("File: " + gffFiles[i].getName() + " Seq size: " + accSet.size() + " Feature size: "
						+ featureList.size());

				for (String acc : accSet) {

					Sequence.Builder sb = Sequence.newBuilder();

					String seqid = acc;
					sb.setAccession(acc);
					sb.setHeader(acc);
					// sb.setFileName(gffFiles[i].getName());

					Feature.Builder fb = null;
					for (SeqFeature seqFeature : featureList) {

						fb = Feature.newBuilder();
						// check if these features belongs to the sequence; have
						// the same accession code
						// System.out.println( " compare ...." +
						// seqFeature.getAccessionNum() + " --" + seqid);
						if (seqFeature.getAccessionNum().equals(seqid)) {
							fb.setAccession(seqFeature.getAccessionNum());
							fb.setSeqid(seqFeature.getSeqid());
							fb.setSource(seqFeature.getSource());
							fb.setFtype(seqFeature.getfType());
							fb.setStart(seqFeature.getStart());
							fb.setEnd(seqFeature.getEnd());
							fb.setScore(seqFeature.getScore());
							fb.setStrand(seqFeature.getStrand());
							fb.setPhase(seqFeature.getPhase());

							// FIXME : after schema changes
							// sb.addFeature(fb.build());

						}

					}

					data.add(sb.build());
				}

				System.out.println("File is done! " + gffFiles[i].getName());

			}
		}

		return data;

	}

	private List<Seq> get_fna(File file) {

		List<Seq> seqList = new ArrayList<Seq>();

		String header = null;
		try {

			FileReader fileReader = new FileReader(file.getAbsolutePath());
			BufferedReader br = new BufferedReader(fileReader);

			String line = "";
			try {
				while ((line = br.readLine()) != null) {
					if (line.substring(0, 1).equals(">")) {
						Seq seq = new Seq();
						seq.setUrl(file.getName());
						header = line;
						seq.setHeader(header);
						String[] head = header.split(" ");
						String strAcc = head[0].substring(1);
						seq.setAccessionNum(strAcc);

						System.out.println("accession code: " + strAcc);

						seqList.add(seq);

					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return seqList;
	}

	private List<SeqFeature> get_gff(File file) {

		List<SeqFeature> featureList = new ArrayList<SeqFeature>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));

			String line = "";
			try {
				while ((line = reader.readLine()) != null) {

					String[] rec = line.split("\t");
					if (rec.length > 2) {

						// System.out.println(" ---seqFeature : " + rec[0]);
						SeqFeature seqFeature = new SeqFeature();
						seqFeature.setAccessionNum(rec[0]);
						seqFeature.setSeqid(rec[0]);
						seqFeature.setSource(rec[1]);
						seqFeature.setfType(rec[2]);
						seqFeature.setStart(Long.parseLong(rec[3]));
						seqFeature.setEnd(Long.parseLong(rec[4]));
						seqFeature.setScore(rec[5]);

						seqFeature.setStrand(rec[6]);

						seqFeature.setPhase(rec[7]);

						featureList.add(seqFeature);
					}

				}

				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return featureList;
	}

	private Set<String> get_Accessions(File file) {

		Set<String> accSet = new HashSet<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));

			String line = "";
			try {
				while ((line = reader.readLine()) != null) {

					String[] rec = line.split("\t");
					if (rec.length > 2) {
						// System.out.println(" ---seqFeature : " + rec[0]);
						accSet.add(rec[0]);
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return accSet;
	}

	

	private static File[] getGFF(String path) {
		File dir = new File(path);
		File[] gffFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".gff");
			}
		});
		return gffFiles;

	}

	private static String get_taxID(File file) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));

			String line = "";
			try {
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("##species ")) {
						String taxID = line.substring(line.indexOf("id=") + 3);
						if (reader != null) {
							reader.close();
						}
						return taxID;
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
		return null;
	}

	private static List<String> get_assemblers(File file) {

//		total_length = 0;
//		total_gap_length = 0;
//		contig_N50 = 0;
//		scaffold_N50 = 0;
//		contig_count = 0;
//		scaffold_count = 0;

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
						if (reader != null) {
							reader.close();
						}
						return assemblers;
					}
//					if (line.startsWith("all")) {
//						String[] data = line.split("\t");
//						// System.out.println(data[4] + ": " +data[5]);
//						if (data[4].equals("scaffold-count")) {
//							scaffold_count = Long.valueOf(data[5]);
//
//						} else if (data[4].equals("scaffold-N50")) {
//							scaffold_N50 = Long.valueOf(data[5]);
//
//						} else if (data[4].equals("contig-count")) {
//							contig_count = Long.valueOf(data[5]);
//
//						} else if (data[4].equals("contig-N50")) {
//							contig_N50 = Long.valueOf(data[5]);
//
//						} else if (data[4].equals("total-gap-length")) {
//							total_gap_length = Long.valueOf(data[5]);
//						} else if (data[4].equals("total-length")) {
//							total_length = Long.valueOf(data[5]);
//
//						}
//
//					}

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
		assemblers.add(" N/A");
		if (reader != null)
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		return assemblers;
	}

	

	// private FType get_Enum(String ftype) {
	//
	//
	// System.out.println( "ftype : " + ftype);
	// return FType.valueOf(ftype);
	// }

	private String getFilePath(String name) {
		name = name.endsWith(".fna") ? name : name + ".fna";
		for (String source : this.sources) {
			if (source.endsWith(name)) {
				return source;
			}
		}
		throw new IllegalArgumentException("No Such file exists in ....");
	}

	@Override
	public String getParserClassName() {
		throw new UnsupportedOperationException();

	}

}
