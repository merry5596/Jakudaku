package passionx3.jkdk.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import passionx3.jkdk.domain.LineItem;

@Mapper
public interface LineItemMapper {
	
	@Select("SELECT lineItemId, orderId, lineNumber, quantity, isDownloaded, saleState, TO_CHAR(i.uploaddate, 'YYYY/MM/DD HH24:MI:SS') AS uploadDate"
			+ "	FROM LINEITEM l, ITEM i"
			+ " WHERE l.itemId = i.itemId AND l.orderId = #{orderId}")
	LineItem getLineItemByOrderId(int orderId);
	
	@Insert("INSERT INTO LINEITEM (lineItemId, orderId, lineNumber, quantity, isDownloaded, itemId, saleState)"
			+ "	VALUES(#{lineItemId}, #{orderId}, #{lineNumber}, #{quantity}, #{isDownloaded}, #{itemId}, #{saleState})")
	int insertLineItem(LineItem lineItem);

	@Select("SELECT lineItemId, orderId, lineNumber, quantity, isDownloaded, saleState, i.itemid, TO_CHAR(i.uploaddate, 'YYYY/MM/DD HH24:MI:SS') AS uploadDate"
			+ "	FROM LINEITEM l, ITEM i"
			+ " WHERE l.orderId = #{orderId} AND l.itemId = i.itemId")
	List<LineItem> getLineItemsByOrderId(int orderId);

}
