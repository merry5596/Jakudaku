package passionx3.jkdk.domain;

import java.util.List;

public class Order {
	int orderId; 
	String orderDate; 
	int totalPrice; 
	String creditCard; 
	String expireDate;
	String cardType;
	String userId;
	int discountCost;
	int usedMileage;
	List<LineItem> lineItems;
		
	public Order() { }

	public Order(int orderId, String orderDate, int totalPrice, String creditCard, String expireDate, String cardType,
			int discountCost, int usedMileage, String userId) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.creditCard = creditCard;
		this.expireDate = expireDate;
		this.cardType = cardType;
		this.discountCost = discountCost;
		this.usedMileage = usedMileage;
		this.userId = userId;
	}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public int getDiscountCost() {
		return discountCost;
	}
	public void setDiscountCost(int discountCost) {
		this.discountCost = discountCost;
	}
	public int getUsedMileage() {
		return usedMileage;
	}
	public void setUsedMileage(int usedMileage) {
		this.usedMileage = usedMileage;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
}
