package boa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

public class PreProcessing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		String strLineTM = "101M �9755 Physeter catodon�_A Chain A, SPERM WHALE MYOGLOBIN F46V N-BUTYL ISOCYANIDE AT PH 9.01MTJ �9755 Physeter catodon�_A Chain A, Phe46(Cd4) Orients The Distal Histidine For Hydrogen Bonding To Bound Ligands In Sperm Whale Myoglobin1MTK �9755 Physeter catodon�_A Chain A, Phe46(cd4) Orients The Distal Histidine For Hydrogen Bonding To Bound Ligands In Sperm Whale Myoglobin1MYM �9755 Physeter catodon�_A Chain A, Structural Determinants Of Co Stretching Vibration Frequencies In Myoglobinð 65022435 49	154aa, 1:154:1:154/96.75%ü\n";
//		for (int i=0 ; i< strLineTM.length(); i++) {
//			
//			System.out.println(" char: " + strLineTM.charAt(i) + " ascii: " +  (int) strLineTM.charAt(i));
//			
//		}

		Calendar cal = Calendar.getInstance();

		//SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

		
		String as_date ="2015-4-7";
		Date dtime = null;
//		try {
//			 dtime = ft.parse(as_date);
//		} catch (ParseException e3) {
//			// TODO Auto-generated catch block
//			e3.printStackTrace();
//		}
		
		dtime = new Date();
		cal.setTime(dtime);
		System.out.println("dtime: "+  dtime.toString() + " get Year: " + cal.get(Calendar.YEAR));
		
		
		long dateLong =dtime.getTime();
		
		cal.setTimeInMillis(dateLong / 1000);
		int year= cal.get(Calendar.YEAR);

		System.out.println("@@@@year: " + year);
		
		dtime = new Date(dateLong);
		cal.setTime(dtime);
		System.out.println("#2 dtime: "+  dtime.toString() + " get Year: " + cal.get(Calendar.YEAR));
		cal.get(Calendar.YEAR);
		System.out.println("#2 @@@@year: " + year);
		
		
		 long start = System.currentTimeMillis( );
		 
         System.out.println(start + "\n");
         
         

		System.out.println(" ascii 1: " + (char) 1);
		System.out.println(" ascii 65533: " + (char) 65533);

		File file = new File(args[0]);
		BufferedWriter file_modified = null;

		// method 3

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
				System.out.println(line);
				// do something with line
			}
		} finally {
			LineIterator.closeQuietly(it);
		}

		// method 2
		FileInputStream fstream = null;
		try {
			fstream = new FileInputStream(args[0]);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		// Read File Line By Line
		try {
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				System.out.println(strLine);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Close the input stream
		try {
			fstream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (sc.hasNextLine()) {
			String line = sc.nextLine();

			String[] annotations = line.split("");
			String[] words = annotations[0].split(" ");
			String seq_id = words[0];
			String ann_id = null;

			for (int i = 0; i < annotations.length; i++) {

				// System.out.println(seq_id + " " + annotations[i]);

				try {
					FileWriter fw = new FileWriter(file.getAbsolutePath() + "_" + "modified_", true); // true tells
																										// to append
																										// data.
					file_modified = new BufferedWriter(fw);
					file_modified.write(seq_id + " " + annotations[i] + "\n");
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

	}

}
