package passionx3.jkdk.dao;

import java.util.List;

import passionx3.jkdk.domain.FundOrder;

public interface FundOrderDao {
	List<FundOrder> getFundOrdersByUserId(String userId);
	FundOrder getFundOrderByOrderId(int orderId);
	int insertFundOrder(FundOrder fundOrder);
}

