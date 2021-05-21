package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.LineItem;
import passionx3.jkdk.domain.Order;

public interface OrderMapper {

	Order getOrder(int orderId);

	int insertOrder(Order order);
	
	@Select("SELECT ORDERID, ORDERDATE, TOTALPRICE, CREDITCARD, EXPIREDATE, CARDTYPE, USEDMILEAGE, DISCOUNTCOST, USERID"
			+ " FROM ORDERS WHERE USERID = #{userId} ORDER BY ORDERDATE")
	List<Order> getOrdersByUserId(String userId);
	Map<String, List<Order>> getLineItemsByUserId(String userId);
}
