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
    	
    	// fundOrder 필드 중 order 테이블 속성에 포함되는 것들의 정보를 order 테이블에 insert
    	int result = orderMapper.insertOrder(fundOrder);
    	if (result == 0)
    		return 0;
    	
    	// 나머지 정보를 fundorder 테이블에 insert
    	result = fundOrderMapper.insertFundOrder(fundOrder);
    	if (result == 0)
    		return 0;
    	
    	// lineitem 테이블에 insert
    	LineItem lineItem = fundOrder.getLineItem();
    	lineItem.setOrderId(fundOrder.getOrderId());
    	lineItem.setLineItemId(sequenceDao.getLineItemSequenceNextVal());
    	result = lineItemMapper.insertLineItem(lineItem);
    	if (result == 0)
    		return 0;
    	
    	return 1;
	}

}
