package passionx3.jkdk.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Sale implements Serializable {

  /* Private Fields */
	private int saleId;
	private int discountRate;
	private String openTime;
	private String closeTime;
	

  /* JavaBeans Properties */
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
}
