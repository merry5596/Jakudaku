package passionx3.jkdk.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Item implements Serializable {
	private int itemId;
	private String producerId;
	private String producerName;
	private int themeId;
	private String themeName;
	private String name;
	private String uploadDate;
	private int price;
	private int likeNum;
	private String thumbnail1;
	private String thumbnail2;
	private String thumbnail3;
	private int isForSale;
	private String description;
	private int approval;
	
	public Item() {
	}
	
	public Item(int itemId, String name, int price, int likeNum, String thumbnail1, int isForSale) {
		this.itemId = itemId;
		this.name = name;
		this.price = price;
		this.likeNum = likeNum;
		this.thumbnail1 = thumbnail1;
		this.isForSale = isForSale;
	}

	public Item(int itemId, String producerId, String producerName, int themeId, String themeName, 
			String name, String uploadDate, int price, int likeNum, String thumbnail1, String thumbnail2, String thumbnail3,
			int isForSale, String description, int approval) {
		this.itemId = itemId;
		this.producerId = producerId;
		this.producerName = producerName;
		this.themeId = themeId;
		this.themeName = themeName;
		this.name = name;
		this.uploadDate = uploadDate;
		this.price = price;
		this.likeNum = likeNum;
		this.thumbnail1 = thumbnail1;
		this.thumbnail2 = thumbnail2;
		this.thumbnail3 = thumbnail3;
		this.isForSale = isForSale;
		this.description = description;
		this.approval = approval;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getProducerId() {
		return producerId;
	}
	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}
	public String getProducerName() {
		return producerName;
	}
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	public int getThemeId() {
		return themeId;
	}
	public void setThemeId(int themeId) {
		this.themeId = themeId;
	}
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getLike() {
		return likeNum;
	}
	public void setLike(int like) {
		this.likeNum = like;
	}
	public String getThumbnail1() {
		return thumbnail1;
	}
	public void setThumbnail1(String thumbnail1) {
		this.thumbnail1 = thumbnail1;
	}
	public String getThumbnail2() {
		return thumbnail2;
	}
	public void setThumbnail2(String thumbnail2) {
		this.thumbnail2 = thumbnail2;
	}
	public String getThumbnail3() {
		return thumbnail3;
	}
	public void setThumbnail3(String thumbnail3) {
		this.thumbnail3 = thumbnail3;
	}
	public int getIsForSale() {
		return isForSale;
	}
	public void setIsForSale(int isForSale) {
		this.isForSale = isForSale;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getApproval() {
		return approval;
	}
	public void setApproval(int approval) {
		this.approval = approval;
	}
	public int getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
}