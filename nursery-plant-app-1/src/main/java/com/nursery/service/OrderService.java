package com.nursery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nursery.exceptions.CustomerException;
import com.nursery.exceptions.OrderException;
import com.nursery.model.Orders;
import com.nursery.model.ShoppingCart;

@Service
public interface OrderService {

	public  Orders addOrder(Orders order, String uuid)throws CustomerException;
	public Orders updateOrder(Orders order,String uuid) throws OrderException;
	public	Orders deleteOrder(Orders order,String uuid)throws OrderException;
	public Orders viewOrder(Integer orderID,String uuid)throws OrderException ;
	public	List<Orders> viewAllOrders(String uuid)throws OrderException;
	public float getCartAmount(List<ShoppingCart> shoppingCartList,String uuid) throws OrderException;
}
