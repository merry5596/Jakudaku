package passionx3.jkdk.dao;

import java.util.List;
import java.util.Map;

import passionx3.jkdk.domain.LineItem;

public interface LineItemDao {
	List<LineItem> getLineItemsByOrderId(int orderId); 	// 주문 별 온라인 상품 list 반환
	Map<String, List<LineItem>> getLineItemsByUserId(String userId); //(orderDate, List<LineItem>) 형식의 Map 반환 
}
