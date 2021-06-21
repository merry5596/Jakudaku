
package passionx3.jkdk.dao;

import java.util.List;

import passionx3.jkdk.domain.LineItem;

public interface LineItemDao {
	LineItem getLineItemByOrderId(int orderId);
	List<LineItem> getLineItemsByOrderId(int orderId);
}