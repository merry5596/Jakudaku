package passionx3.jkdk.domain;

public class Online extends Item {
	private int saleState;
	private String pcFile;
	private String tabletFile;
	private String phoneFile;
	
	public int getSaleState() {
		return saleState;
	}
	public void setSaleState(int saleState) {
		this.saleState = saleState;
	}
	public String getPcFile() {
		return pcFile;
	}
	public void setPcFile(String pcFile) {
		this.pcFile = pcFile;
	}
	public String getTabletFile() {
		return tabletFile;
	}
	public void setTabletFile(String tabletFile) {
		this.tabletFile = tabletFile;
	}
	public String getPhoneFile() {
		return phoneFile;
	}
	public void setPhoneFile(String phoneFile) {
		this.phoneFile = phoneFile;
	}
	
}
