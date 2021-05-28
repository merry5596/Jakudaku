package passionx3.jkdk.dao.mybatis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import passionx3.jkdk.dao.LineItemDao;
import passionx3.jkdk.dao.OrderDao;
import passionx3.jkdk.dao.SequenceDao;
import passionx3.jkdk.dao.mybatis.mapper.FundOrderMapper;
import passionx3.jkdk.dao.mybatis.mapper.LineItemMapper;
import passionx3.jkdk.dao.mybatis.mapper.OrderMapper;
import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.LineItem;
import passionx3.jkdk.domain.Order;

@Repository
public class MybatisOrderDao implements OrderDao {

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private FundOrderMapper fundOrderMapper;
	
	@Autowired
	private LineItemMapper lineItemMapper;

	@Autowired
	private LineItemDao lineItemDao;
	
	@Autowired
	private SequenceDao sequenceDao;
	
	@Override
	@Transactional
	public Order getOrderByOrderId(int orderId) throws DataAccessException {
		Order order = orderMapper.getOrderByOrderId(orderId);
		if (order != null) {
			order.setLineItems(lineItemDao.getLineItemsByOrderId(orderId));
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
    	for (LineItem lineItem : order.getLineItems()) {
    		lineItem.setOrderId(order.getOrderId());
    		lineItemMapper.insertLineItem(lineItem);
    	}
    	
    	return 1;
	}

	@Override
	public List<Order> getOrdersByUserId(String userId) {
		return orderMapper.getOrdersByUserId(userId);
	}
	
	// 재아 파트
	@Override
	public Map<String, List<Order>> getLineItemsByUserId(String userId) {
		List<Order> onlineList = orderMapper.getOrdersByUserId(userId);
		List<FundOrder> fundingList = fundOrderMapper.getFundOrdersByUserId(userId);
		
		Map<String, List<Order>> lineItemMap = new LinkedHashMap<>();
		
		int i = 0, j = 0;
		
		while(i < onlineList.size() && j < fundingList.size()) {
			List<Order> orderList = new ArrayList<Order>();
			int orderId = onlineList.get(i).getOrderId();
			int fundOrderId = fundingList.get(i).getOrderId();
			
			if(onlineList.get(i).getOrderDate().equals(fundingList.get(j).getOrderDate())) {
				Order order = orderMapper.getOrderByOrderId(orderId);
				order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
				FundOrder fundOrder = fundOrderMapper.getFundOrder(fundOrderId);
				fundOrder.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
				orderList.add(order);
				orderList.add(fundOrder);
				
				lineItemMap.put(onlineList.get(i).getOrderDate(), orderList);
				i++; j++;
			}
			else if(Integer.parseInt(onlineList.get(i).getOrderDate()) > Integer.parseInt(fundingList.get(j).getOrderDate())) {
				Order order = orderMapper.getOrderByOrderId(orderId);
				order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
				orderList.add(order);
				
				lineItemMap.put(onlineList.get(i).getOrderDate(), orderList);
				i++;
			}
			else {
				FundOrder fundOrder = fundOrderMapper.getFundOrder(fundOrderId);
				fundOrder.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
				orderList.add(fundOrder);
				
				lineItemMap.put(fundingList.get(j).getOrderDate(), orderList);
				
				j++;
			}
		}
		
		List<Order> orderList = new ArrayList<Order>();
		
		if(i < onlineList.size()) {
			for(int k = i; k < onlineList.size(); k++) {
				int orderId = onlineList.get(i).getOrderId();

				Order order = orderMapper.getOrderByOrderId(orderId);
				order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));
				orderList.add(order);
				
				lineItemMap.put(onlineList.get(i).getOrderDate(), orderList);
				i++;
			}
		}
		else {
			for(int k = j; k < fundingList.size(); k++) {
				int fundOrderId = fundingList.get(i).getOrderId();

				FundOrder fundOrder = fundOrderMapper.getFundOrder(fundOrderId);
				fundOrder.setLineItems(lineItemMapper.getLineItemsByOrderId(fundOrderId));
				orderList.add(fundOrder);
				
				lineItemMap.put(fundingList.get(j).getOrderDate(), orderList);
				
				j++;
			}
		}
		
		
		return lineItemMap;
	}

}
