package passionx3.jkdk.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Online extends Item implements Serializable {
	private int saleState;
	private double totalRate;
	private String pcFile;
	private String tabletFile;
	private String phoneFile;
	
	public Online() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Online(int itemId, String name, int price, int likeNum, String thumbnail1, int isForSale, double totalRate, String pcFile, String tabletFile, String phoneFile) {
		super(itemId, name, price, likeNum, thumbnail1, isForSale);
		this.totalRate = totalRate;
		this.pcFile = pcFile;
		this.tabletFile = tabletFile;
		this.phoneFile = phoneFile;
	}

	public Online(int saleState, double totalRate, String pcFile, String tabletFile, String phoneFile) {
		super();
		this.saleState = saleState;
		this.totalRate = totalRate;
		this.pcFile = pcFile;
		this.tabletFile = tabletFile;
		this.phoneFile = phoneFile;
	}



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
	public double getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(double totalRate) {
		this.totalRate = totalRate;
	}
	
	//method 추가
	public int getDiscount() {
		if (saleState == 0)
			return 0;
		else
			return (int) (getPrice() * 0.1);
	}
	public int getSalePrice() {
		return getPrice() - getDiscount();
	}
}
