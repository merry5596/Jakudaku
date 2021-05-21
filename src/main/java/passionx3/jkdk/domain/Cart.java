package passionx3.jkdk.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.support.PagedListHolder;

@SuppressWarnings("serial")
public class Cart implements Serializable {

  /* Private Fields */

  private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());
	
  private final PagedListHolder<CartItem> itemList = new PagedListHolder<CartItem>();

  /* JavaBeans Properties */

	public Cart() {
		this.itemList.setPageSize(4);
	}

	public Iterator<CartItem> getAllCartItems() { return itemList.getSource().iterator(); }
  public PagedListHolder<CartItem> getCartItemList() { return itemList; }
  public int getNumberOfItems() { return itemList.getSource().size(); }

  /* Public Methods */

  public boolean containsItemId(String itemId) {
    return itemMap.containsKey(itemId);
  }

  public void addItem(Online item, boolean isInStock) {
    CartItem cartItem = itemMap.get(item.getItemId());
    if (cartItem == null) {
      cartItem = new CartItem();
      cartItem.setOnlineItem(item);
      cartItem.setQuantity(0);
      cartItem.setInStock(isInStock);
      itemMap.put(String.valueOf(item.getItemId()), cartItem);
      itemList.getSource().add(cartItem);
    }
    cartItem.incrementQuantity();
  }


  public Online removeItemById(String itemId) {
    CartItem cartItem = itemMap.remove(itemId);
    if (cartItem == null) {
      return null;
    }
		else {
      itemList.getSource().remove(cartItem);
      return cartItem.getOnlineItem();
    }
  }

  public void incrementQuantityByItemId(String itemId) {
    CartItem cartItem = itemMap.get(itemId);
    cartItem.incrementQuantity();
  }

  public void setQuantityByItemId(String itemId, int quantity) {
    CartItem cartItem = itemMap.get(itemId);
    cartItem.setQuantity(quantity);
  }

  public double getSubTotal() {
    double subTotal = 0;
    Iterator<CartItem> items = getAllCartItems();
    while (items.hasNext()) {
      CartItem cartItem = (CartItem) items.next();
      Online onlineItem = cartItem.getOnlineItem();
      
      // double listPrice = item.getListPrice(); => 이 부분 수정 좀
      int quantity = cartItem.getQuantity();
      //subTotal += listPrice * quantity;
    }
    return subTotal;
  }

}
