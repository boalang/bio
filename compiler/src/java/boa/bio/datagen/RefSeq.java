package boa.bio.datagen;

import java.util.HashMap;
import java.util.PriorityQueue;

public class RefSeq {

	public static void main(String[] args) {
		
		HashMap<String, String> hmap= new HashMap<String, String>();
		
		PriorityQueue<String> queue = 
	            new PriorityQueue<String>();
	        queue.add("short");
	        hmap.put("short", "this is short");
	        queue.add("ago");
	        hmap.put("ago", "this is ago");
	        queue.add("arvin");
	        hmap.put("arvin", "arvin gyannn");
	        while (queue.size() != 0)
	        {
	        	
	        	    String key=queue.remove();
	            System.out.println(key + " "+hmap.get(key));
	        }
	        
	        queue.add("short");
	        queue.add("ago");
	        queue.add("arvin");
	        
	        for(String k: queue){
	        	
	        	System.out.println(k);
	        }
	}
}
