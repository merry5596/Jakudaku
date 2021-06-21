package passionx3.jkdk.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartItem implements Serializable {

  /* Private Fields */

  private Online onlineItem;

  /* JavaBeans Properties */
	public Online getOnlineItem() {
		return onlineItem;
	}
	public void setOnlineItem(Online onlineItem) {
		this.onlineItem = onlineItem;
	}
	

}
