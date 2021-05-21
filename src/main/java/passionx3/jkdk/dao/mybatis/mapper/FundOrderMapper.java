package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.FundOrder;

public interface FundOrderMapper {

	FundOrder getFundOrder(int orderId);

	int insertFundOrder(FundOrder fundOrder);

	@Select("SELECT ORDERID, ORDERDATE, TOTALPRICE, CREDITCARD, EXPIREDATE, CARDTYPE, USEDMILEAGE, DISCOUNTCOST, USERID, "
			+ "ADDRESS1, ADDRESS2, ZIP, RECEIVERNAME, DELIVERYNUMBER, DELIVERYSTATUS"
			+ " FROM ORDERS, FUNDORDERS"
			+ " WHERE USERID = #{userId} AND ORDERS.ORDERID = FUNDORDERS.ORDERID  ORDER BY ORDERDATE")
	List<FundOrder> getFundOrdersByUserId(String userId);	 

}
