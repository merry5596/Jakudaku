package passionx3.jkdk.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TimeSale extends Sale implements Serializable {

  /* Private Fields */
	private int TimeSaleId;
	private int itemId;
	private int quantity;

	/* JavaBeans Properties */
	public int getTimeSaleId() {
		return TimeSaleId;
	}
	public void setTimeSaleId(int timeSaleId) {
		TimeSaleId = timeSaleId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
