package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.FundOrder;
@Mapper
public interface FundOrderMapper {

	@Select("SELECT o.orderDate, o.totalPrice, o.creditCard, o.expireDate, o.cardType, o.userId, o.discountCost, o.usedMileage,"
			+ "	f.address1, f.address2, f.zip, f.phone, f.receiverName, f.deliveryNumber, f.deliveryStatus "
			+ "	FROM FundOrders f, orders o"
			+ " WHERE f.orderId = #{orderId} AND f.orderId = o.orderId")
	FundOrder getFundOrder(int orderId);
	
	@Select("INSERT INTO FundOrders (orderId, address1, address2, zip, phone, receiverName, deliveryNumber, deliveryStatus)"
			+ "	VALUES(#{orderId}, #{address1}, #{address2}, #{zip}, #{phone}, #{receiverName}, #{deliveryNumber}, #{deliveryStatus})")
	int insertFundOrder(FundOrder fundOrder);

	@Select("SELECT ORDERID, ORDERDATE, TOTALPRICE, CREDITCARD, EXPIREDATE, CARDTYPE, USEDMILEAGE, DISCOUNTCOST, USERID, "
			+ "ADDRESS1, ADDRESS2, ZIP, PHONE, RECEIVERNAME, DELIVERYNUMBER, DELIVERYSTATUS"
			+ " FROM ORDERS, FUNDORDERS"
			+ " WHERE USERID = #{userId} AND ORDERS.ORDERID = FUNDORDERS.ORDERID  ORDER BY ORDERDATE")
	List<FundOrder> getFundOrdersByUserId(String userId);	 

}
