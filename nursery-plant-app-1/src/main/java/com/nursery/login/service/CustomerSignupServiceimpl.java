package com.nursery.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.CustomerException;
import com.nursery.login.model.CurrentUserSession;
import com.nursery.login.repository.CurrentUserSessiondDao;
import com.nursery.login.repository.CustomerDao;
import com.nursery.model.Customer;


@Service
public class CustomerSignupServiceimpl implements CustomerSignupService{


	@Autowired
	private CustomerDao adao;
	
	@Autowired
	private CurrentUserSessiondDao cusd;
	@Override
	public Customer SignupCustomer(Customer customer) {
		
		Customer Customer1=adao.save(customer);
		//332311
		return Customer1;
	
	}

	@Override
	public Customer UpdateCustomer(Customer customer,String key) throws CustomerException{
	CurrentUserSession cu=	cusd.findByuuid(key).orElseThrow(()-> new CustomerException("User Not Logged in"));
	if(customer.getCustomerID()==cu.getUserId()) {
		return adao.save(customer);
	}
	else
		throw new CustomerException("Please Enter a correct id");
	
	}

}
