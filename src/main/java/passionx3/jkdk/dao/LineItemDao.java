
package passionx3.jkdk.dao;

import java.util.List;

import passionx3.jkdk.domain.LineItem;

public interface LineItemDao {
	LineItem getLineItemByOrderId(int orderId);	// 주문 별 온라인 상품 반환 (Funding 해당)
	List<LineItem> getLineItemsByOrderId(int orderId); 	// 주문 별 온라인 상품 list 반환
}