package com.nursery.login.service;

import com.nursery.exceptions.CustomerException;
import com.nursery.login.model.CustomerLoginDTO;

public interface CustomerLoginService {

	public String Customerlogin(CustomerLoginDTO al) throws CustomerException;
	
	public String Customerlogout(String key) throws CustomerException;
 
}
