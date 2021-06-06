package passionx3.jkdk.controller;

import java.io.Serializable;

import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.Order;


@SuppressWarnings("serial")
public class FundOrderForm implements Serializable {

	private final FundOrder fundOrder;
	private boolean isNewAddress = false;
	//private boolean shippingAddressRequired = false;
	//private boolean shippingAddressProvided = false;
	
	public FundOrderForm(FundOrder fundOrder) {
		this.fundOrder = fundOrder;
	}

	public FundOrder getFundOrder() {
		return fundOrder;
	}

	public boolean getIsNewAddress() {
		return isNewAddress;
	}

	public void setIsNewAddress(boolean isNewAddress) {
		this.isNewAddress = isNewAddress;
	}
	
	

//	public void setShippingAddressRequired(boolean shippingAddressRequired) {
//		this.shippingAddressRequired = shippingAddressRequired;
//	}
//
//	public boolean isShippingAddressRequired() {
//		return shippingAddressRequired;
//	}
//
//	public void setShippingAddressProvided(boolean shippingAddressProvided) {
//		this.shippingAddressProvided = shippingAddressProvided;
//	}
//
//	public boolean didShippingAddressProvided() {
//		return shippingAddressProvided;
//	}
}
