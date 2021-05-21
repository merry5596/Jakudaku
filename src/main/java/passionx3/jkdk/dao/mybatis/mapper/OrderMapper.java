package passionx3.jkdk.dao.mybatis.mapper;

import passionx3.jkdk.domain.Order;

public interface OrderMapper {

	Order getOrder(int orderId);

	int insertOrder(Order order);
}
