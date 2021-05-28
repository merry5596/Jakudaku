package passionx3.jkdk.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Online extends Item implements Serializable {
	private Integer categoryId;
	private String categoryName;
	private double totalRate;
	private String pcFile;
	private String tabletFile;
	private String phoneFile;
	private int saleState;
	
	public Online() {
		super();
	}

	public Online(int itemId, String name, int price, int likeNum, String thumbnail1, int isForSale, double totalRate, String pcFile, String tabletFile, String phoneFile, int saleState) {
		super(itemId, name, price, likeNum, thumbnail1, isForSale);
		this.totalRate = totalRate;
		this.pcFile = pcFile;
		this.tabletFile = tabletFile;
		this.phoneFile = phoneFile;
		this.saleState = saleState;
	}

	public Online(int saleState, double totalRate, String pcFile, String tabletFile, String phoneFile) {
		super();
		this.saleState = saleState;
		this.totalRate = totalRate;
		this.pcFile = pcFile;
		this.tabletFile = tabletFile;
		this.phoneFile = phoneFile;
	}

	public Online(int categoryId, String categoryName, double totalRate, String pcFile, String tabletFile,
			String phoneFile, int saleState) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.totalRate = totalRate;
		this.pcFile = pcFile;
		this.tabletFile = tabletFile;
		this.phoneFile = phoneFile;
		this.saleState = saleState;
	}

	public Online(int itemId, String producerId, String producerName, int themeId, String themeName, String name,
			String uploadDate, int price, int likeNum, String thumbnail1, String thumbnail2, String thumbnail3,
			int isForSale, String description, int categoryId, String categoryName, double totalRate, String pcFile, String tabletFile,
			String phoneFile, int saleState) {
		super(itemId, producerId, producerName, themeId, themeName, name, uploadDate, price, likeNum, thumbnail1, thumbnail2,
				thumbnail3, isForSale, description);
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.totalRate = totalRate;
		this.pcFile = pcFile;
		this.tabletFile = tabletFile;
		this.phoneFile = phoneFile;
		this.saleState = saleState;
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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
