package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.FundOrder;

@Mapper
public interface FundOrderMapper {

	@Select("SELECT o.orderId, TO_CHAR(o.orderdate, 'YY/MM/DD HH24:MI:SS') as orderdate, o.totalPrice, o.creditCard, o.expireDate, o.cardType, o.discountCost, o.usedMileage, o.userId,"
			+ "	f.address1, f.address2, f.zip, f.phone, f.receiverName, f.deliveryNumber, f.deliveryStatus"
			+ "	FROM FUNDORDERS f, ORDERS o"
			+ " WHERE f.orderId = #{orderId} AND f.orderId = o.orderId")
	FundOrder getFundOrderByOrderId(int orderId);
	
	@Insert("INSERT INTO FUNDORDERS (orderId, address1, address2, zip, phone, receiverName, deliveryNumber, deliveryStatus)"
			+ "	VALUES(#{orderId}, #{address1}, #{address2}, #{zip}, #{phone}, #{receiverName}, #{deliveryNumber}, #{deliveryStatus})")
	int insertFundOrder(FundOrder fundOrder);

	@Select("SELECT ORDERS.ORDERID, ORDERDATE, TOTALPRICE, CREDITCARD, EXPIREDATE, CARDTYPE, USEDMILEAGE, DISCOUNTCOST, USERID, "
			+ "ADDRESS1, ADDRESS2, ZIP, PHONE, RECEIVERNAME, DELIVERYNUMBER, DELIVERYSTATUS"
			+ " FROM ORDERS, FUNDORDERS"
			+ " WHERE USERID = #{userId} AND ORDERS.ORDERID = FUNDORDERS.ORDERID  ORDER BY ORDERDATE DESC")
	List<FundOrder> getFundOrdersByUserId(String userId);	 

}
