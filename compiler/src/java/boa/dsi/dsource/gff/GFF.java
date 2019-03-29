package boa.dsi.dsource.gff;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

public class GFF {

	private String path;

	public GFF(String path) {
		this.path = path;
	}

	public boolean isReadable(String source) {
		// TODO Auto-generated method stub
		return false;
	}

	public GenomicsData getData() {

		GenomicsData genomics = new GenomicsData();


		List<Seq> sequencesList = new ArrayList<Seq>();
		List<SeqFeature> featuresList = new ArrayList<SeqFeature>();


		ArrayList<SeqFeature[]> data = new ArrayList<SeqFeature[]>();

		String fileName = null;
		File folder = new File(path);
		File[] listofFiles = folder.listFiles();
		// (dir, name) -> {
		// return name.toLowerCase().endsWith(".gff");
		// }

		for (int i = 0; i < listofFiles.length; i++) {

			if (listofFiles[i].isFile()) {
				fileName = listofFiles[i].getName().toString();

				// if file is fna fill the sequence object, then add to the
				// arraylist

				if (FilenameUtils.getExtension(fileName).equals("fna")) {

					String header = null;
					try {
						System.out.println("fileName " + FilenameUtils.removeExtension(listofFiles[i].getPath()));
						FileReader fileReader = new FileReader(listofFiles[i].getPath());
				        BufferedReader br = new BufferedReader(fileReader);
				            
						String line="";
						try {
							while ((line=br.readLine()) != null){
								if (line.substring(0,1).equals(">")){
									Seq seq = new Seq();
									seq.setUrl(fileName);
									header =line;
									seq.setHeader(header);
									String[] head = header.split(" ");
									String strAcc= head[0].substring(1);
									seq.setAccessionNum(strAcc);

									//System.out.println("accession code: " + strAcc);
									
									sequencesList.add(seq);

								}
								
							}
							

						} catch (IOException e) {
							e.printStackTrace();
						}

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}


				}
				// if file is gff then fill the feature, then add to the
				// arraylist

				else if (FilenameUtils.getExtension(fileName).equals("gff")) {

					try {
						FileReader file = new FileReader(listofFiles[i].getPath());
						BufferedReader reader = new BufferedReader(file);

						String line = "";
						try {
							while ((line = reader.readLine()) != null) {

								String[] rec = line.split("\t");
								if (rec.length > 2) {

									//System.out.println(" ---seqFeature : " + rec[0]);
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

									featuresList.add(seqFeature);
								}

							}
						} catch (IOException e) {
							e.printStackTrace();
						}

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

				}

			}

		}

		genomics.setFeatures(featuresList);
		genomics.setSequences(sequencesList);
		return genomics;
	}



//	private FType getFType(int code) {
//		return FType.valueOf(code);
//	}

	public static void main(String[] args) {

		String path = "/Users/hbagheri/Documents/bio/testncbi";
		GFF gff =new GFF(path);
		gff.getData();
		

	}

}
