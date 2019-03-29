package boa.dsi.dsource.gff;

public class SeqFeature {
	String accessionNum;
	String seqid;
	String source;
	String fType;
	long start;
	long end;
	String score;
	String strand;
	String phase;
	String[] attributes;
  
	
	public String getAccessionNum() {
		return accessionNum;
	}
	public void setAccessionNum(String accessionNum) {
		this.accessionNum = accessionNum;
	}
	public String getSeqid() {
		return seqid;
	}
	public void setSeqid(String seqid) {
		this.seqid = seqid;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getfType() {
		return fType;
	}
	public void setfType(String fType) {
		this.fType = fType;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getStrand() {
		return strand;
	}
	public void setStrand(String strand) {
		this.strand = strand;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String[] getAttributes() {
		return attributes;
	}
	public void setAttributes(String[] attributes) {
		this.attributes = attributes;
	}
	
	
	
	
	
	

}
