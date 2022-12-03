package com.nursery.login.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.CustomerException;
import com.nursery.login.model.CurrentUserSession;
import com.nursery.login.model.CustomerLoginDTO;
import com.nursery.login.repository.CurrentUserSessiondDao;
import com.nursery.login.repository.CustomerDao;
import com.nursery.model.Customer;

import net.bytebuddy.utility.RandomString;


@Service
public class CustomerLoginServiceimpl implements CustomerLoginService{
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CurrentUserSessiondDao sessiondDao;
	

	@Override
	public String Customerlogin(CustomerLoginDTO dto) throws CustomerException {
		Customer existingCustomer= customerDao.findBycustomerName(dto.getCustomerName());
		
		if(existingCustomer == null) {
			
			throw new CustomerException("Please Enter a valid mobile number");
			
			 
		}
		Optional<CurrentUserSession> validCustomerSessionOpt =  sessiondDao.findById(existingCustomer.getCustomerID());
		
		
		
		
		
		if(validCustomerSessionOpt.isPresent()) {
			
			throw new CustomerException("User already Logged In with this number");
			
		}
		
		if(existingCustomer.getPassword().equals(dto.getPassword())) {
			
			String key= RandomString.make(6);
			
			
			
			CurrentUserSession currentUserSession = new CurrentUserSession();
			currentUserSession.setId(existingCustomer.getCustomerID());
			currentUserSession.setLocalDateTime(LocalDateTime.now());
			currentUserSession.setUuid(key);
			
			sessiondDao.save(currentUserSession);

			return currentUserSession.toString();
		}
		else
			throw new CustomerException("Please Enter a valid password");
		
	}

	@Override
	public String Customerlogout(String key) throws CustomerException {
Optional<CurrentUserSession> validCustomerSession = sessiondDao.findByuuid(key);
		
		
		if(validCustomerSession.get() == null) {
			throw new CustomerException("User Not Logged In with this number");
			
		}
		
		
		sessiondDao.delete(validCustomerSession.get());
		
		
		return "Logged Out !";
		
	}

}
