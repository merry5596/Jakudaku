
package passionx3.jkdk.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LineItem implements Serializable {
	int lineItemId;
	int orderId;
	int lineNumber;
	int quantity;
	int isDownloaded;
	int unitPrice;

	Item item;
	int saleState;

	public LineItem() {
	}
		
	public LineItem(int lineItemId, int orderId, int lineNumber, int quantity, int isDownloaded, 
			int unitPrice, Item item, int saleState) {
  		this.lineItemId = lineItemId;
		  this.orderId = orderId;
		  this.lineNumber = lineNumber;
		  this.quantity = quantity;
	  	this.isDownloaded = isDownloaded;
	  	this.unitPrice = unitPrice;
	  	this.item = item;
	  	this.saleState = saleState;
	}
  
	public LineItem(int lineNumber, CartItem cartItem) {
	    this.lineNumber = lineNumber;
	    this.quantity = cartItem.getQuantity();
	    this.item.setItemId(cartItem.getOnlineItem().getItemId());
	    //this.unitPrice = cartItem.getOnlineItem().getListPrice();
	    this.item = cartItem.getOnlineItem();
	  }
	

	
	public int getLineItemId() {
		return lineItemId;
	}
	public void setLineItemId(int lineItemId) {
		this.lineItemId = lineItemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getIsDownloaded() {
		return isDownloaded;
	}
	public void setIsDownloaded(int isDownloaded) {
		this.isDownloaded = isDownloaded;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getSaleState() {
		return saleState;
	}
	public void setSaleState(int saleState) {
		this.saleState = saleState;
	}
	
	//method 추가
	public int getDiscount() {
		if (saleState == 0)
			return 0;
		else
			return (int) (unitPrice * 0.1);
	}
	public int getSalePrice() {
		return unitPrice - getDiscount();
	}
}
