package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import passionx3.jkdk.dao.FundOrderDao;
import passionx3.jkdk.dao.LineItemDao;
import passionx3.jkdk.dao.SequenceDao;
import passionx3.jkdk.dao.mybatis.mapper.FundOrderMapper;
import passionx3.jkdk.dao.mybatis.mapper.FundingMapper;
import passionx3.jkdk.dao.mybatis.mapper.LineItemMapper;
import passionx3.jkdk.dao.mybatis.mapper.OrderMapper;
import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.LineItem;

@Repository
public class MybatisFundOrderDao implements FundOrderDao {

	@Autowired
	private FundOrderMapper fundOrderMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private LineItemMapper lineItemMapper;
	
	@Autowired
	private FundingMapper fundingMapper;

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private LineItemDao lineItemDao;
	
	@Override
	public List<FundOrder> getFundOrdersByUserId(String userId) {
		return fundOrderMapper.getFundOrdersByUserId(userId);
	}

	@Override
	@Transactional
	public FundOrder getFundOrderByOrderId(int orderId) throws DataAccessException {
		FundOrder fundOrder = fundOrderMapper.getFundOrderByOrderId(orderId);
		if (fundOrder != null) {
			fundOrder.setLineItem(lineItemDao.getLineItemByOrderId(orderId));
		}
	    return fundOrder;
	}
	
	@Override
	@Transactional
	public int insertFundOrder(FundOrder fundOrder) throws DataAccessException {
    	fundOrder.setOrderId(sequenceDao.getOrderSequenceNextVal());
    	
    	int result = orderMapper.insertOrder(fundOrder);
    	if (result == 0)
    		return 0;
    	
    	result = fundOrderMapper.insertFundOrder(fundOrder);
    	if (result == 0)
    		return 0;
    	
    	LineItem lineItem = fundOrder.getLineItem();
    	lineItem.setOrderId(fundOrder.getOrderId());
    	lineItem.setLineItemId(sequenceDao.getLineItemSequenceNextVal());
    	result = lineItemMapper.insertLineItem(lineItem);
    	if (result == 0)
    		return 0;
    	
    	fundingMapper.updatePurchaseQuantity(fundOrder.getLineItem().getItemId(), fundOrder.getLineItem().getQuantity());
    	
    	return 1;
	}

}
