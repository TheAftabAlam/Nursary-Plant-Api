package com.nursery.login.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nursery.exceptions.AdminException;
import com.nursery.login.model.Admin;
import com.nursery.login.model.AdminLoginDTO;
import com.nursery.login.model.CurrentUserSession;
import com.nursery.login.repository.AdminDao;
import com.nursery.login.repository.CurrentUserSessiondDao;

import net.bytebuddy.utility.RandomString;



@Service
public class AdminLoginServiceimpl implements AdminLoginService{
	
	@Autowired
	private AdminDao adminDao;
	
	
	@Autowired
	private CurrentUserSessiondDao sessiondDao;

	

	



	@Override
	public String Adminlogin(AdminLoginDTO dto) throws AdminException {
		
		Admin existingCustomer= adminDao.findByadminName(dto.getAdminName());
		
		if(existingCustomer == null) {
			
			throw new AdminException("Please Enter a valid mobile number");
			
			 
		}
		
		
		
		
		Optional<CurrentUserSession> validCustomerSessionOpt =  sessiondDao.findById(existingCustomer.getUserId());
		
		
		
		
		
		if(validCustomerSessionOpt.isPresent()) {
			
			throw new AdminException("User already Logged In with this number");
			
		}
		
		if(existingCustomer.getPassword().equals(dto.getPassword())) {
			
			String key= RandomString.make(6);
			
			
			
			CurrentUserSession currentUserSession = new CurrentUserSession();
			currentUserSession.setId(existingCustomer.getUserId());
			currentUserSession.setLocalDateTime(LocalDateTime.now());
			currentUserSession.setUuid(key);
			
			sessiondDao.save(currentUserSession);

			return currentUserSession.toString();
		}
		else
			throw new AdminException("Please Enter a valid password");
		
		
	}







	@Override
	public String Adminlogout(String key) throws AdminException {

		Optional<CurrentUserSession> validCustomerSession = sessiondDao.findByuuid(key);
		
		
		if(validCustomerSession.get() == null) {
			throw new AdminException("User Not Logged In with this number");
			
		}
		
		
		sessiondDao.delete(validCustomerSession.get());
		
		
		return "Logged Out !";
		
		
	}

	
}
