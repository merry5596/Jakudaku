package passionx3.jkdk.dao;

import java.util.List;

import passionx3.jkdk.domain.FundOrder;

public interface FundOrderDao {
	List<FundOrder> getFundOrdersByUserId(String userId);    	// 사용자별 펀딩 상품 주문 list 반환
	FundOrder getFundOrderByOrderId(int orderId);	// orderId로 FundOrder 반환
	int insertFundOrder(FundOrder fundOrder); 	// Fundorder 레코드 추가
}

