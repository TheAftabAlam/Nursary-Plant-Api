package com.nursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.CustomerException;
import com.nursery.exceptions.OrderException;
import com.nursery.login.model.CurrentUserSession;
import com.nursery.login.repository.CurrentUserSessiondDao;
import com.nursery.login.repository.CustomerDao;
import com.nursery.model.Customer;
import com.nursery.model.Orders;
import com.nursery.model.Plant;
import com.nursery.model.Seed;
import com.nursery.model.ShoppingCart;
import com.nursery.repository.OrderDao;
import com.nursery.repository.PlantDao;
import com.nursery.repository.SeedDao;

@Service
public class OrderServiceImpl implements OrderService{

	
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CurrentUserSessiondDao cusd;
	
	@Autowired
	private PlantDao plantDao;
	
	@Autowired
	private SeedDao seedDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	
	@Override
	public Orders addOrder(Orders order,String uuid) throws CustomerException {
		CurrentUserSession cus = cusd.findByuuid(uuid).orElseThrow(() -> new CustomerException("Please login First..."));
		
		Optional<Customer> customer = customerDao.findById(cus.getId());
		if(customer.isPresent())
		{
			Orders newOrder= orderDao.save(order);
			return newOrder;
		}
		else
			throw new CustomerException("Access denied!");
		
	}

	@Override
	public Orders updateOrder(Orders order,String uuid) throws OrderException{	
CurrentUserSession cus = cusd.findByuuid(uuid).orElseThrow(() -> new OrderException("Please login First..."));
		
		Optional<Customer> customer = customerDao.findById(cus.getId());
		if(customer.isPresent())
		{
			Optional<Orders> optional=orderDao.findById(order.getBookingOrderId());
			
			if(optional.isPresent())
			{
				return orderDao.save(order);
			}
			else {
				throw new OrderException("Order not found");
			}
		}
		else
			throw new OrderException("Access denied!");
		
	}

	@Override
	public Orders deleteOrder(Orders order,String uuid)throws OrderException {
CurrentUserSession cus = cusd.findByuuid(uuid).orElseThrow(() -> new OrderException("Please login First..."));
		
		Optional<Customer> customer = customerDao.findById(cus.getId());
		if(customer.isPresent())
		{
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
		else
			throw new OrderException("Access denied!");
		
		
		//
		
		//
	}

	@Override
	public Orders viewOrder(Integer orderID,String uuid)throws OrderException {
CurrentUserSession cus = cusd.findByuuid(uuid).orElseThrow(() -> new OrderException("Please login First..."));
		
		Optional<Customer> customer = customerDao.findById(cus.getId());
		if(customer.isPresent())
		{
			Optional<Orders> optional=orderDao.findById(orderID);
			if(optional.isPresent())
			{
				
				return optional.get();
			}
			else {
				throw new OrderException("Order not found");
			}
			
			
		}
		else
			throw new OrderException("Access denied!");
		
		
		
		
		
		
		
		
		
		
		
		
	}

	@Override
	public List<Orders> viewAllOrders(String uuid) throws OrderException{
CurrentUserSession cus = cusd.findByuuid(uuid).orElseThrow(() -> new OrderException("Please login First..."));
		
		Optional<Customer> customer = customerDao.findById(cus.getId());
		if(customer.isPresent())
		{
			List<Orders> orders= orderDao.findAll();
			if(orders.size()>0)
			{
				return orders;
			}
			else {
				throw new OrderException("No order found");
			}
			
		}
		else
			throw new OrderException("Access denied!");
		
		
		
		
		
		
		
		
	}
	
	@Override
	public float getCartAmount(List<ShoppingCart> shoppingCartList,String uuid) throws OrderException {
		
CurrentUserSession cus = cusd.findByuuid(uuid).orElseThrow(() -> new OrderException("Please login First..."));
		
		Optional<Customer> customer = customerDao.findById(cus.getId());
		if(customer.isPresent())
		{
			


	        float totalCartAmount = 0f;
	        float singleCartAmount = 0f;
	        int availableQuantity = 0;

	        for (ShoppingCart cart : shoppingCartList) {

	            int productId = cart.getProductId();
	            Optional<Plant> optional = plantDao.findById(productId);
	            Plant plant=optional.get();
//	            System.out.println("Plant details "+plant);
	            if(plant!=null)
	            {
	            	
	            	if(plant.getPlantStock()<=0)
	            	{
	            		throw new OrderException("plant stock size is zero");
	            	}
	            	
	                if (plant.getPlantStock() < cart.getQuantity()) {
	                    singleCartAmount = (float) (plant.getPlantCost() * plant.getPlantStock());
	                    cart.setQuantity(plant.getPlantStock());
	                } else {
	                    singleCartAmount = (float) (cart.getQuantity() * plant.getPlantCost());
	                    availableQuantity = plant.getPlantStock() - cart.getQuantity();
	                }
	                totalCartAmount = totalCartAmount + singleCartAmount;
	               
	               plant.setPlantStock(availableQuantity);
	                availableQuantity=0;
	                cart.setProductName(plant.getCommonName());
	                cart.setAmount(singleCartAmount);
	                plantDao.save(plant);
	                
	            }
	            
	            else
	            {
	            	Optional<Seed> optional2=seedDao.findById(productId);
	 	            Seed seed=optional2.get();
//	 	            System.out.println("Seed details "+seed);
	 	            if(seed!=null)
	 	            {
	 	            	if(seed.getSeedStock()<=0)
		            	{
		            		throw new OrderException("seed stock size is zero");
		            	}
		            	
	 	            	
	 	            	
	 	            	if (seed.getSeedStock()< cart.getQuantity()) {
	 	                    singleCartAmount = (float) (seed.getSeedCost() * seed.getSeedStock());
	 	                    cart.setQuantity(seed.getSeedStock());
	 	                } else {
	 	                    singleCartAmount = (float) (cart.getQuantity() * seed.getSeedCost());
	 	                    availableQuantity = seed.getSeedStock() - cart.getQuantity();
	 	                }
	 	                totalCartAmount = totalCartAmount + singleCartAmount;
	 	               seed.setSeedStock(availableQuantity);
	 	                availableQuantity=0;
	 	                cart.setProductName(seed.getCommonName());
	 	                cart.setAmount(singleCartAmount);
	 	               seedDao.save(seed);
	 	            }
	            }
	          
	        }
	       
	        return totalCartAmount;
	    }
	
		
		else
			throw new OrderException("Access denied!");
		
		
		
		
	}
		
		
		
		
}

