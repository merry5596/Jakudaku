package passionx3.jkdk.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Sale implements Serializable {

  /* Private Fields */
	private int saleId;
	private int discountRate;
	private Date openTime;
	private Date closeTime;
	

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
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
}
