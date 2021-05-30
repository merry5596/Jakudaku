package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.Order;

@Mapper
public interface OrderMapper {

	@Select("SELECT orderId, orderDate, totalPrice, creditCard, expireDate, cardType, discountCost, usedMileage, userId"
			+ "	FROM ORDERS WHERE orderId = #{orderId}")
	Order getOrderByOrderId(@Param("orderId") int orderId);

	@Insert("INSERT INTO ORDERS (orderId, orderDate, totalPrice, creditCard, expireDate, cardType, discountCost, usedMileage, userId)"
			+ "	VALUES(#{orderId}, #{orderDate}, #{totalPrice}, #{creditCard}, #{expireDate}, #{cardType}, #{discountCost}, #{usedMileage}, #{userId})")
	int insertOrder(@Param("order") Order order);
	
	@Select("SELECT ORDERID, ORDERDATE, TOTALPRICE, CREDITCARD, EXPIREDATE, CARDTYPE, USEDMILEAGE, DISCOUNTCOST, USERID"
			+ " FROM ORDERS WHERE USERID = #{userId} ORDER BY ORDERDATE")
	List<Order> getOrdersByUserId(String userId);
	Map<String, List<Order>> getLineItemsByUserId(@Param("userId") String userId);
}
