package passionx3.jkdk.controller;

import java.io.Serializable;

import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.Order;


@SuppressWarnings("serial")
public class OrderForm implements Serializable {

	private final Order order;
	
	public OrderForm(Order order) {
		this.order = order;
	}

	public Order getOrder() {
		return order;
	}

}
