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

  private final Map<Integer, CartItem> itemMap = Collections.synchronizedMap(new HashMap<Integer, CartItem>());
	
  private final PagedListHolder<CartItem> itemList = new PagedListHolder<CartItem>();

  /* JavaBeans Properties */

	public Cart() {
		this.itemList.setPageSize(4);
	}

	public Iterator<CartItem> getAllCartItems() { return itemList.getSource().iterator(); }
  public PagedListHolder<CartItem> getCartItemList() { return itemList; }
  public int getNumberOfItems() { return itemList.getSource().size(); }

  /* Public Methods */

  public boolean containsItemId(int itemId) {
    return itemMap.containsKey(itemId);
  }

  public void addItem(Online item) {
    CartItem cartItem = itemMap.get(item.getItemId());
    
    if (cartItem == null) {
      cartItem = new CartItem();
      cartItem.setOnlineItem(item);
      itemMap.put(item.getItemId(), cartItem);
      itemList.getSource().add(cartItem);
    }
  }

  public Online removeItemById(int itemId) {
    CartItem cartItem = itemMap.remove(itemId);
    if (cartItem == null) {
      return null;
    }
		else {
      itemList.getSource().remove(cartItem);
      return cartItem.getOnlineItem();
    }
  }

  // 할인 안된 금액 반환
  public int getSubTotal() {
    int subTotal = 0;
    Iterator<CartItem> items = getAllCartItems();
    while (items.hasNext()) {
      CartItem cartItem = (CartItem) items.next();
      Online onlineItem = cartItem.getOnlineItem();
      
      subTotal += onlineItem.getPrice();
    }
    return subTotal;
  }
  
  public void removeAllItems() {
	  itemMap.clear();
	  itemList.getSource().clear();
	  
  }
  
  public int getActualTotal() {
	  int actualTotal = 0;
	  Iterator<CartItem> items = getAllCartItems();
	  while (items.hasNext()) {
	      CartItem cartItem = (CartItem) items.next();
	      Online onlineItem = cartItem.getOnlineItem();
	      
	      actualTotal += onlineItem.getSalePrice();
	  }
	  return actualTotal;
  }
}
