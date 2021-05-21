package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
<<<<<<< HEAD

import passionx3.jkdk.domain.LineItem;
=======
>>>>>>> branch 'develop' of https://github.com/KangHyojin1401/Jakudaku.git

<<<<<<< HEAD
=======
import passionx3.jkdk.domain.LineItem;
>>>>>>> branch 'develop' of https://github.com/KangHyojin1401/Jakudaku.git
@Mapper
public interface LineItemMapper {
	
	LineItem getLineItemByOrderId(int orderId);

	void insertLineItem(LineItem lineItem);

	List<LineItem> getLineItemsByOrderId(int orderId);

}
