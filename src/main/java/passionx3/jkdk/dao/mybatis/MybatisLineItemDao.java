package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import passionx3.jkdk.dao.LineItemDao;
import passionx3.jkdk.dao.mybatis.mapper.FundingMapper;
import passionx3.jkdk.dao.mybatis.mapper.LineItemMapper;
import passionx3.jkdk.dao.mybatis.mapper.OnlineMapper;
import passionx3.jkdk.domain.LineItem;

@Repository
public class MybatisLineItemDao implements LineItemDao {
	@Autowired
	private LineItemMapper lineItemMapper;
	@Autowired
	private OnlineMapper onlineMapper;
	@Autowired
	private FundingMapper fundingMapper;

// funding
	@Override
	public LineItem getLineItemByOrderId(int orderId) {
		LineItem lineItem = lineItemMapper.getLineItemByOrderId(orderId);
		if (lineItem != null) {
			lineItem.setItem(fundingMapper.getFundingItemByLineItemId(lineItem.getLineItemId()));
		}
		return null;
	}
	
//	online
	@Override
	public List<LineItem> getLineItemsByOrderId(int orderId) {
		List<LineItem> lineItems = lineItemMapper.getLineItemsByOrderId(orderId);
		for (LineItem lineItem : lineItems) {
			lineItem.setItem(onlineMapper.getOnlineItemByLineItemId(lineItem.getLineItemId()));
		}
		return lineItems;
	}

}
