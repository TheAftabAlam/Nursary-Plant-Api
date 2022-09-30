package com.nursery.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.nursery.exceptions.OrderException;
import com.nursery.model.Order;
import com.nursery.repository.OrderDao;
import com.nursery.service.OrderServices;

public class orderServiceImpl implements OrderServices {
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public Order addOrder(Order order) {
		Order newOrder= orderDao.save(order);
		return newOrder;
	}

	@Override
	public Order updateOrder(Order order) throws OrderException {
		Optional<Order> optional=
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
	public Order deleteOrder(Order order)throws OrderException {
		Optional<Order> optional=
				orderDao.findById(order.getBookingOrderId());
		
		if(optional.isPresent())
		{
			Order order2=optional.get();
			orderDao.delete(order2);
			return order2;
		}
		else {
			throw new OrderException("Order not found");
		}
	}

	@Override
	public Order viewOrder(Integer orderID)throws OrderException {
		Optional<Order> optional=orderDao.findById(orderID);
		if(optional.isPresent())
		{
			
			return optional.get();
		}
		else {
			throw new OrderException("Order not found");
		}
		
		
	}

	@Override
	public List<Order> viewAllOrders() throws OrderException{
		List<Order> orders= orderDao.findAll();
		if(orders.size()>0)
		{
			return orders;
		}
		else {
			throw new OrderException("No order found");
		}
		
	}

}
