package com.nursery.login.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.CustomerException;
import com.nursery.login.model.CurrentUserSession;
import com.nursery.login.model.CustomerLogin;
import com.nursery.login.repository.CurrentUserSessiondDao;
import com.nursery.login.repository.CustomerDao;
import com.nursery.login.repository.CustomerLoginDao;
import com.nursery.model.Customer;

@Service
public class CustomerLoginServiceimpl implements CustomerLoginService{
	@Autowired
	private CustomerLoginDao cld;
	
	@Autowired
	private CustomerDao cd;

	@Autowired
	private CurrentUserSessiondDao cusd; 
	@Override
	public String Customerlogin(CustomerLogin al) throws CustomerException {
		Customer customer= cd.findById(al.getUserId()).orElseThrow(()->new CustomerException("Customer not found"));
		if(!customer.getCustomerName().equals(al.getCustomerName())) {
			 throw new CustomerException("Incorrect username");
		}
		if(!customer.getPassword().equals(al.getPassword())) {
			throw new CustomerException("Incorrect Password");			
		}
		
//		Provision such that one customer can login at a time
		List<CurrentUserSession> session = cusd.findAll();
		if(session.size()>0) {
			return "Someone Already logged in";
		}
		
		 Optional<CurrentUserSession> cu=cusd.findByuserId(al.getUserId());
		if(cu.isPresent()) {
			throw new CustomerException("User Alredy Loged in");	
		}
		CurrentUserSession cus= new CurrentUserSession();
		RandomNumservice rns=new RandomNumservice();
		cus.setUserId(al.getUserId());
		cus.setNow(LocalDateTime.now());
		cus.setUuid(rns.RandomNumber());
		
		cld.save(al);
	CurrentUserSession current=	cusd.save(cus);
		
		return current.toString();
	}

	@Override
	public String Customerlogout(String key) throws CustomerException {
		CurrentUserSession cus=cusd.findByuuid(key).orElseThrow(()->new CustomerException("Please Enter Right key"));
		CustomerLogin cu=cld.findById(cus.getUserId()).orElseThrow(()->new CustomerException("Customer Not Availble"));
		cld.delete(cu);
		cusd.delete(cus);
				
		return "LogOut successful";
	}

	
}
