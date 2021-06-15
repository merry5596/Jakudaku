package passionx3.jkdk.domain;

import java.io.Serializable;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public abstract class Item implements Serializable {
	private int itemId;
	private String producerId;
	private String producerName;
	private Integer themeId;
	private String themeName;
	private String name;
	private String uploadDate;
	private int price;
	private int likeNum;
	private String thumbnail1;
	private String thumbnail2;
	private String thumbnail3;
	
	private MultipartFile[] mulThumbnail1;
//	private MultipartFile mulThumbnail2;
//	private MultipartFile mulThumbnail3;
	
	private int isForSale;
	private String description;
	private int approval;
	
	public Item() {
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
	public Integer getThemeId() {
		return themeId;
	}
//	public void setThemeId(int themeId) {
//		this.themeId = themeId;
//	}
	public void setThemeId(Integer themeId) {
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
	
	public MultipartFile[] getMulThumbnail1() {
		return mulThumbnail1;
	}
	public void setMulThumbnail1(MultipartFile[] mulThumbnail1) {
		this.mulThumbnail1 = mulThumbnail1;
	}
//	public MultipartFile getMulThumbnail2() {
//		return mulThumbnail2;
//	}
//	public void setMulThumbnail2(MultipartFile mulThumbnail2) {
//		this.mulThumbnail2 = mulThumbnail2;
//	}
//	public MultipartFile getMulThumbnail3() {
//		return mulThumbnail3;
//	}
//	public void setMulThumbnail3(MultipartFile mulThumbnail3) {
//		this.mulThumbnail3 = mulThumbnail3;
//	}
	
	
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
	
	public String getThumbnail1Path() {
	      System.out.println("item: " + uploadDate);
	      SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	      Date to = null;
	      try {
	         to = transFormat.parse(uploadDate);
	      } catch (ParseException e1) {
	         e1.printStackTrace();
	      }

	      System.out.println("item: " + uploadDate);
	      
	      transFormat = new SimpleDateFormat("yyMMdd");
	      String file_uploadDate = transFormat.format(to);
//	      String uploadPath = Paths.get("C:", "jkdk", "upload", file_uploadDate).toString();
//	      
//	      return uploadPath + uploadDate;
	      

	      String uploadPath = Paths.get("thumbnail", file_uploadDate).toString();
	      System.out.println("/thumbnail/" + file_uploadDate);
	      return "/thumbnail/" + file_uploadDate;
	   }
}