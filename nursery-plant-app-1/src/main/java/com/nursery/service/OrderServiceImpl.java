package com.nursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.CustomerException;
import com.nursery.exceptions.OrderException;
import com.nursery.login.model.CurrentUserSession;
import com.nursery.login.repository.CurrentUserSessiondDao;
import com.nursery.model.Orders;
import com.nursery.repository.OrderDao;

@Service
public class OrderServiceImpl implements OrderService{

	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CurrentUserSessiondDao cusd;
	
	@Override
	public Orders addOrder(Orders order,String uuid) throws CustomerException {
		CurrentUserSession cus = cusd.findByuuid(uuid).orElseThrow(() -> new CustomerException("Please login First..."));
		
		Orders newOrder= orderDao.save(order);
		return newOrder;
	}

	@Override
	public Orders updateOrder(Orders order) throws OrderException {
		Optional<Orders> optional=
				orderDao.findById(order.getBookingOrderId());
		
		if(optional.isPresent())
		{
			return orderDao.save(order);
		}
		else {
			throw new OrderException("Order not found");
		}
	}

	@Override
	public Orders deleteOrder(Orders order)throws OrderException {
		Optional<Orders> optional=
				orderDao.findById(order.getBookingOrderId());
		
		if(optional.isPresent())
		{
			Orders order2=optional.get();
			orderDao.delete(order2);
			return order2;
		}
		else {
			throw new OrderException("Order not found");
		}
	}

	@Override
	public Orders viewOrder(Integer orderID)throws OrderException {
		Optional<Orders> optional=orderDao.findById(orderID);
		if(optional.isPresent())
		{
			
			return optional.get();
		}
		else {
			throw new OrderException("Order not found");
		}
		
		
	}

	@Override
	public List<Orders> viewAllOrders() throws OrderException{
		List<Orders> orders= orderDao.findAll();
		if(orders.size()>0)
		{
			return orders;
		}
		else {
			throw new OrderException("No order found");
		}
		
	}
	
}

