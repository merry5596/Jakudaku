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
    // 아직 담기지 않았을 때
    if (cartItem == null) {
      cartItem = new CartItem();
      cartItem.setOnlineItem(item);
	  System.out.println("Cart.java - if(cartItem == null): " + cartItem.getOnlineItem().getItemId());

      itemMap.put(item.getItemId(), cartItem);
      itemList.getSource().add(cartItem);
    }
    // 이미 담겼을 때, 처리 필요?
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

  // 할인까지 된 총 금액을 반환합니다!!! (DB 저장 아니므로 출력용 정보인 할인된 금액이 더 중요하기 때문)
  public int getSubTotal() {
    int subTotal = 0;
    Iterator<CartItem> items = getAllCartItems();
    while (items.hasNext()) {
      CartItem cartItem = (CartItem) items.next();
      Online onlineItem = cartItem.getOnlineItem();
      
      subTotal += onlineItem.getSalePrice();
    }
    return subTotal;
  }
  
  //메소드 추가
  public void removeAllItems() {
	  itemMap.clear();
	  itemList.getSource().clear();
	  
  }
}
