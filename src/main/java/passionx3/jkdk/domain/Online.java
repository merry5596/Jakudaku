package passionx3.jkdk.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class Online extends Item implements Serializable {
	@NotNull(message="카테고리를 선택하세요.")
	private Integer categoryId;
	private String categoryName;
	private Double totalRate;
	private String pcFile;
	private String tabletFile;
	private String phoneFile;
	
	private MultipartFile mulPcFile;
	private MultipartFile mulTabletFile;
	private MultipartFile mulPhoneFile;
	
	private int saleState;
	
	public Online() {
		super();
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
	
	public MultipartFile getMulPcFile() {
		return mulPcFile;
	}
	public void setMulPcFile(MultipartFile mulPcFile) {
		this.mulPcFile = mulPcFile;
	}
	public MultipartFile getMulTabletFile() {
		return mulTabletFile;
	}
	public void setMulTabletFile(MultipartFile mulTabletFile) {
		this.mulTabletFile = mulTabletFile;
	}
	public MultipartFile getMulPhoneFile() {
		return mulPhoneFile;
	}
	public void setMulPhoneFile(MultipartFile mulPhoneFile) {
		this.mulPhoneFile = mulPhoneFile;
	}
	
	public Double getTotalRate() {
		return Math.round(totalRate * 10) / 10.0;
	}
	public void setTotalRate(Double totalRate) {
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
