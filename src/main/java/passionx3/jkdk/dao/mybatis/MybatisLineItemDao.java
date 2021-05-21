package passionx3.jkdk.dao.mybatis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import passionx3.jkdk.dao.LineItemDao;
import passionx3.jkdk.dao.mybatis.mapper.FundOrderMapper;
import passionx3.jkdk.dao.mybatis.mapper.LineItemMapper;
import passionx3.jkdk.dao.mybatis.mapper.OrderMapper;
import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.Item;
import passionx3.jkdk.domain.LineItem;
import passionx3.jkdk.domain.Order;

public class MybatisLineItemDao implements LineItemDao {

	private LineItemMapper lineItemMapper;
	private OrderMapper orderMapper;
	private FundOrderMapper fundOrderMapper;
	
	
	@Override
	public List<LineItem> getLineItemsByOrderId(int orderId) {
		return lineItemMapper.getLineItemsByOrderId(orderId);
	}

}
