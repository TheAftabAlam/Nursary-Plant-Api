package com.nursery.login.service;

import com.nursery.exceptions.CustomerException;
import com.nursery.model.Customer;

public interface CustomerSignupService {

	public Customer SignupCustomer(Customer customer);
	 
	public Customer UpdateCustomer(Customer customer,String key) throws CustomerException;
}
