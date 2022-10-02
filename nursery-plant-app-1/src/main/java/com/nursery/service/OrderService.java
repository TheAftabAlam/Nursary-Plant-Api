package com.nursery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nursery.exceptions.CustomerException;
import com.nursery.exceptions.OrderException;
import com.nursery.model.Orders;

@Service
public interface OrderService {

	Orders addOrder(Orders order, String uuid)throws CustomerException;
	Orders updateOrder(Orders order) throws OrderException;
	Orders deleteOrder(Orders order)throws OrderException;
	Orders viewOrder(Integer orderID)throws OrderException;
	List<Orders> viewAllOrders()throws OrderException;
}
