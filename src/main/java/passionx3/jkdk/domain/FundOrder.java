
package passionx3.jkdk.domain;

public class FundOrder extends Order {
	String address1;
	String address2;
	int zip;
	String phone;
	String receiverName;
	String deliveryNumber;
	int deliveryStatus;
	LineItem lineItem;
	
	public FundOrder() { }
	
	public FundOrder(int orderId, String orderDate, int totalPrice, String creditCard, String expireDate, String cardType,
			int discountCost, int usedMileage, String userId, String address1, String address2, int zip, 
			String phone, String receiverName, String deliveryNumber, int deliveryStatus, LineItem lineItem) {
		super(orderId, orderDate, totalPrice, creditCard, expireDate, cardType, discountCost, usedMileage, userId);
		this.address1 = address1;
		this.address2 = address2;
		this.zip = zip;
		this.phone = phone;
		this.receiverName = receiverName;
		this.deliveryNumber = deliveryNumber;
		this.deliveryStatus = deliveryStatus;
		this.lineItem = lineItem;
	}

	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getDeliveryNumber() {
		return deliveryNumber;
	}
	public void setDeliveryNumber(String deliveryNumber) {
		this.deliveryNumber = deliveryNumber;
	}
	public int getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public LineItem getLineItem() {
		return lineItem;
	}
	public void setLineItem(LineItem lineItem) {
		this.lineItem = lineItem;
	}
	
	
}

