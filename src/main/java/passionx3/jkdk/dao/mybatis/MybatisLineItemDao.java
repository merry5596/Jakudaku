package passionx3.jkdk.dao.mybatis;

import java.util.List;
import java.util.Map;

import passionx3.jkdk.dao.LineItemDao;
import passionx3.jkdk.dao.mybatis.mapper.LineItemMapper;
import passionx3.jkdk.domain.LineItem;

public class MybatisLineItemDao implements LineItemDao {

	private LineItemMapper lineItemMapper;
	
	@Override
	public List<LineItem> getLineItemsByOrderId(int orderId) {
		return lineItemMapper.getLineItemsByOrderId(orderId);
	}

	// 재아 파트
	@Override
	public Map<String, List<LineItem>> getLineItemsByUserId(String userId) {
		return null;
	}
}
