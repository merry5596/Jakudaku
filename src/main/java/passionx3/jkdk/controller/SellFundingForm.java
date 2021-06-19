package passionx3.jkdk.controller;

import java.io.Serializable;

import javax.validation.Valid;

import passionx3.jkdk.domain.Funding;

@SuppressWarnings("serial")
public class SellFundingForm implements Serializable {
	@Valid
	private Funding funding;

	private boolean newFunding;

	public SellFundingForm(Funding funding) {
		this.funding = funding;
		this.newFunding = false;
	}

	public SellFundingForm() {
		this.funding = new Funding();
		this.newFunding = true;
	}

	public Funding getFunding() {
		return funding;
	}

	public boolean isNewFunding() {
		return newFunding;
	}
	
}
