package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import passionx3.jkdk.domain.LineItem;
import passionx3.jkdk.domain.LineItem;

@Mapper
public interface LineItemMapper {
	
	LineItem getLineItemByOrderId(int orderId);

	void insertLineItem(LineItem lineItem);

	List<LineItem> getLineItemsByOrderId(int orderId);

}
