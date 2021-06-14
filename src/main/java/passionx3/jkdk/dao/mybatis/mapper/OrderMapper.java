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
	@Select("SELECT orderId, TO_CHAR(orderdate, 'YY/MM/DD HH24:MI:SS') as orderdate, totalPrice, creditCard, expireDate, cardType, discountCost, usedMileage, userId"
			+ "	FROM ORDERS, dual WHERE orderId = #{orderId}")
	Order getOrderByOrderId(@Param("orderId") int orderId);

	@Insert("INSERT INTO ORDERS (orderId, orderDate, totalPrice, creditCard, expireDate, cardType, discountCost, usedMileage, userId)"
			+ "	VALUES(#{orderId}, sysdate, #{totalPrice}, #{creditCard}, #{expireDate}, #{cardType}, #{discountCost}, #{usedMileage}, #{userId})")
	int insertOrder(Order order);
	
	@Select("SELECT ORDERID, TO_CHAR(orderDate, 'YYYY/MM/DD HH24:MI:SS') AS orderDate, TOTALPRICE, CREDITCARD, EXPIREDATE, CARDTYPE, USEDMILEAGE, DISCOUNTCOST, USERID"
			+ " FROM ORDERS WHERE USERID = #{userId} ORDER BY ORDERDATE DESC")
	List<Order> getOrdersByUserId(String userId);

}