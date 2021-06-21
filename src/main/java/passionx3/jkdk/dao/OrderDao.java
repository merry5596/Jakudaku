
package passionx3.jkdk.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import passionx3.jkdk.domain.Order;

public interface OrderDao {
	List<Order> getOrdersByUserId(String userId);
	Order getOrderByOrderId(int orderId);
	int insertOrder(Order order);
	Map<String, List<Order>> getLineItemsByUserId(String userId) throws ParseException;
}
