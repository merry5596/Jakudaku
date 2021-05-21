package passionx3.jkdk.controller;

import java.io.Serializable;

import passionx3.jkdk.domain.Online;

@SuppressWarnings("serial")
public class SellOnlineForm implements Serializable {

	private Online online;

	private boolean newOnline;

	public SellOnlineForm(Online online) {
		this.online = online;
		this.newOnline = false;
	}

	public SellOnlineForm() {
		this.online = new Online();
		this.newOnline = true;
	}

	public Online getOnline() {
		return online;
	}

	public boolean isNewOnline() {
		return newOnline;
	}
	
}
