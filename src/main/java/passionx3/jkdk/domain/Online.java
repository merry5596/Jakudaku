package passionx3.jkdk.domain;

import java.util.Date;

public class Online extends Item {
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

	public Online(int itemId, String producerId, String producerName, int themeId, String themeName, int categoryId,
			String name, Date uploadDate, int price, int like, String thumbnail1, String thumbnail2, String thumbnail3,
			int isForSale, String description, int approval, int saleState, double totalRate, String pcFile, String tabletFile, String phoneFile) {
		super(itemId, producerId, producerName, themeId, themeName, categoryId, name, uploadDate, price, like, thumbnail1,
				thumbnail2, thumbnail3, isForSale, description, approval);
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
