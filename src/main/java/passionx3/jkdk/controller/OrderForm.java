package passionx3.jkdk.controller;

import java.io.Serializable;

import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.Order;


@SuppressWarnings("serial")
public class OrderForm implements Serializable {

	private final Order order;
	private final FundOrder fundOrder;
	private boolean shippingAddressRequired = false;
	private boolean shippingAddressProvided = false;
	
	public OrderForm(Order order) {
		this.order = order;
		this.fundOrder = null;
	}
	
	public OrderForm(FundOrder fundOrder) {
		this.fundOrder = fundOrder;
		this.order = null;
	}

	public Order getOrder() {
		if (fundOrder != null)
			return fundOrder;
		
		return order;
	}

	public void setShippingAddressRequired(boolean shippingAddressRequired) {
		this.shippingAddressRequired = shippingAddressRequired;
	}

	public boolean isShippingAddressRequired() {
		return shippingAddressRequired;
	}

	public void setShippingAddressProvided(boolean shippingAddressProvided) {
		this.shippingAddressProvided = shippingAddressProvided;
	}

	public boolean didShippingAddressProvided() {
		return shippingAddressProvided;
	}
}
