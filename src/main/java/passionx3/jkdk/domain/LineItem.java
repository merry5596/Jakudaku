package passionx3.jkdk.domain;

public class LineItem {
	int lineItemId;
	int orderId;
	int lineNumber;
	int quantity;
	int isDownloaded;
	int unitPrice;
	Item item;
	
	
	public LineItem() {
	}
		
	public LineItem(int lineItemId, int orderId, int lineNumber, int quantity, int isDownloaded, 
			int unitPrice, Item item) {
		this.lineItemId = lineItemId;
		this.orderId = orderId;
		this.lineNumber = lineNumber;
		this.quantity = quantity;
		this.isDownloaded = isDownloaded;
		this.unitPrice = unitPrice;
		this.item = item;
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
	
}
