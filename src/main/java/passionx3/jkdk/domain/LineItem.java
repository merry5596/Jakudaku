package passionx3.jkdk.domain;

public class LineItem {
	int lineItemId;
	int orderId;
	int lineNumber;
	int quantity;
	int isDownloaded;
	int itemId;
	String itemName;
	String thumbnail; 
	int unitPrice;
	
	
	public LineItem() {
	}
		
	public LineItem(int lineItemId, int orderId, int lineNumber, int quantity, int isDownloaded, int itemId, String itemName,
			String thumbnail, int unitPrice) {
		this.lineItemId = lineItemId;
		this.orderId = orderId;
		this.lineNumber = lineNumber;
		this.quantity = quantity;
		this.isDownloaded = isDownloaded;
		this.itemName = itemName;
		this.thumbnail = thumbnail;
		this.unitPrice = unitPrice;
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
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
}
