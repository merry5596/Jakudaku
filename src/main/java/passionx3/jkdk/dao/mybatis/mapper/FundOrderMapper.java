package passionx3.jkdk.dao.mybatis.mapper;

import passionx3.jkdk.domain.FundOrder;

public interface FundOrderMapper {

	FundOrder getFundOrder(int orderId);

	int insertFundOrder(FundOrder fundOrder);

}
