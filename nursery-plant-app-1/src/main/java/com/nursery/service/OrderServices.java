package com.nursery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nursery.exceptions.OrderException;
import com.nursery.model.Order;



@Service
public interface OrderServices  {

	Order addOrder(Order order);
	Order updateOrder(Order order) throws OrderException;
	Order deleteOrder(Order order)throws OrderException;
	Order viewOrder(Integer orderID)throws OrderException;
	List<Order> viewAllOrders()throws OrderException;
}
