package passionx3.jkdk.controller;

import java.io.Serializable;

import passionx3.jkdk.domain.FundOrder;


@SuppressWarnings("serial")
public class FundOrderForm implements Serializable {

	private final FundOrder fundOrder;
	private boolean isNewAddress = false;
	
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
	

}
