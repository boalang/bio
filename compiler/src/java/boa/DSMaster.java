package boa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;


import com.aol.cyclops.data.async.Queue;
import com.google.protobuf.GeneratedMessage;

import boa.dsi.dsource.bio.Genomics;
import boa.dsi.dsource.fars.FARS;
import boa.dsi.evaluator.EvaluationEngine;
import boa.dsi.storage.sequencefile.SequenceFileStorage;

public class DSMaster {
	
	public static void main(String[] args) {	
		
		/*
		 * Genomics Input
		 */
		String program = args[0]; // input boa source file (*.boa)
		String inputlocation = args[1];  // where are the inputs stored
		String output = args[2]; // where should the output go
		
	  		
		
		/*
		 * In case we need to create the data from scratch, then what are the raw data sources
		 */
		ArrayList<String> sources = new ArrayList<String>();
		
		//String pathAddr ="/Users/hbagheri/Documents/NCBI/testncbi";
		//sources.clear();
		//sources.add(pathAddr);
		Genomics external = new Genomics(sources);
		
		// generate data source for genomics
		
		// what kind of storage system are you interested in using
		// inputlocation:  where to save processed data
		// external : Which AbstractDataSource to use to parse the data
		SequenceFileStorage storage = new SequenceFileStorage(inputlocation, external);
		
		// what kind of evaluation engine to be used in here. Currently using Boa
		// program: boa program to use
		// arg2: where is input data
		// output: where to write output
		System.out.println("storage.getDataLocation(): " + storage.getDataLocation());
		EvaluationEngine engine = new EvaluationEngine(program, storage.getDataLocation(), output);

		
		/*
		 * All the components of the system are added and connected to each other with proper communication interfacing.
		 * Now we can start reading raw data and convert it to processed form, store it using aforementioned storage technology
		 * and evaluate the queries over stored data.
		 */
		
		  //Queue<GeneratedMessage> eSourceIStorage = new Queue<GeneratedMessage>(new LinkedBlockingQueue<GeneratedMessage>());
		  //external.getDataInQueue(eSourceIStorage);
		  
		  // UnDoc following command to create new sequence file
		  //storage.store(eSourceIStorage);
		  
		  //To generate sequence file uncomment following line
		  //storage.store(rawPath);
		
		
		// evaluate the queries using engine 
		engine.evaluate();
		
		/*
		 * if succesfully evalauted then display the results 
		 */
		if (engine.isSuccess()) {
			System.out.println();
			System.out.println(engine.getResult());
			// Writing the output to the file
			
		}
		// storage.getDataInQueue(iStorageEEngine);
		// System.out.println(iStorageEEngine.get());
	}
}
