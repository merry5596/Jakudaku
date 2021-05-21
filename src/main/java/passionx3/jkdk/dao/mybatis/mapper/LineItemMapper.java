package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import passionx3.jkdk.domain.LineItem;

public interface LineItemMapper {

	List<LineItem> getLineItemsByOrderId(int orderId);
	
	LineItem getLineItemByOrderId(int orderId);

	void insertLineItem(LineItem lineItem);

}
