package passionx3.jkdk.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CartItem implements Serializable {

  /* Private Fields */

  private Online onlineItem;
  private int quantity;
  private boolean inStock;

  /* JavaBeans Properties */
	public Online getOnlineItem() {
		return onlineItem;
	}
	public void setOnlineItem(Online onlineItem) {
		this.onlineItem = onlineItem;
	}
	
  public boolean isInStock() { return inStock; }
  public void setInStock(boolean inStock) { this.inStock = inStock; }
  public int getQuantity() { return quantity; }
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

//	public double getTotalPrice() {
//		if (item != null) {
//			return item.getListPrice() * quantity;
//		}
//		else {
//			return 0;
//		}
//	}

  /* Public methods */

  public void incrementQuantity() {
    quantity++;
  }

}
