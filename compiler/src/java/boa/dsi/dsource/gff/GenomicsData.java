package boa.dsi.dsource.gff;

import java.util.ArrayList;
import java.util.List;

public class GenomicsData {

	private List<Seq> sequences=new ArrayList<Seq>();
	private List<SeqFeature> features=new ArrayList<SeqFeature>();
	
	
	public List<Seq> getSequences() {
		return sequences;
	}
	public void setSequences(List<Seq> sequences) {
		this.sequences = sequences;
	}
	public List<SeqFeature> getFeatures() {
		return features;
	}
	public void setFeatures(List<SeqFeature> features) {
		this.features = features;
	}
	
	
	
	
}
