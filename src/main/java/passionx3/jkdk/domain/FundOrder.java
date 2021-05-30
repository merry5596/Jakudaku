
package passionx3.jkdk.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class FundOrder extends Order implements Serializable {
	String address1;
	String address2;
	String zip;
	String phone;
	String receiverName;
	String deliveryNumber;
	int deliveryStatus;
	LineItem lineItem;
	
	public FundOrder() { }
	
	public FundOrder(int orderId, String orderDate, int totalPrice, String creditCard, String expireDate, String cardType,
			int discountCost, int usedMileage, String userId, String address1, String address2, String zip, 
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

	public void initFundOrder(Account account, Funding funding, int quantity) {
		lineItems = new ArrayList<LineItem>();
		
		userId = account.getUserId();
		
		Calendar cal= Calendar.getInstance ( );
		SimpleDateFormat sDate = new SimpleDateFormat("yy/MM/dd");
		cal.setTime(new Date());
		String today = sDate.format(cal.getTime());
		orderDate = today;

		lineItem = new LineItem(lineItems.size() + 1, funding, quantity);
		totalPrice = funding.getPrice() * quantity;
		
		//creditCard = "0";
		//expireDate = "12/03";
		//cardType = "Visa";
		
		addLineItem(lineItem);
		
		discountCost = 0;
		
		usedMileage = 0;
		earningMileage = 0;
		
		address1 = account.getAddress1();
		address2 = account.getAddress2();
		zip = account.getZip();
		phone = account.getPhone();
		receiverName = account.getName();
		
		deliveryNumber = "0";
		deliveryStatus = 0;
		
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
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

