package passionx3.jkdk.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import passionx3.jkdk.dao.OrderDao;
import passionx3.jkdk.dao.SequenceDao;
import passionx3.jkdk.dao.mybatis.mapper.LineItemMapper;
import passionx3.jkdk.dao.mybatis.mapper.OrderMapper;
import passionx3.jkdk.domain.LineItem;
import passionx3.jkdk.domain.Order;

public class MybatisOrderDao implements OrderDao {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private LineItemMapper lineItemMapper;

	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	@Transactional
	public Order getOrder(int orderId) throws DataAccessException {
		Order order = orderMapper.getOrder(orderId);
		if (order != null) {
			order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
		}
	    return order;
	}
	
	@Override
	@Transactional
	public int insertOrder(Order order) throws DataAccessException {  
    	order.setOrderId(sequenceDao.getNextId("ordernum"));
    	
    	// order 테이블에 insert
    	int result = orderMapper.insertOrder(order);
    	if (result == 0)
    		return 0;

    	// lineitem 테이블에 insert
    	for (int i = 0; i < order.getLineItems().size(); i++) {
    		LineItem lineItem = (LineItem) order.getLineItems().get(i);
    		lineItem.setOrderId(order.getOrderId());
    		lineItemMapper.insertLineItem(lineItem);
    	}
    	return 1;
	}

	@Override
	public List<Order> getOrdersByUserId(String userId) {
		return null;
	}
	
}
