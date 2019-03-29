package boa.dsi.dsource.gff;

public class Seq {

	String accessionNum;
	String header;
	String url;
	SeqFeature[] feature;
	
	
	public String getAccessionNum() {
		return accessionNum;
	}
	public void setAccessionNum(String accessionNum) {
		this.accessionNum = accessionNum;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public SeqFeature[] getFeature() {
		return feature;
	}
	public void setFeature(SeqFeature[] feature) {
		this.feature = feature;
	}
	
	
	
	
}
