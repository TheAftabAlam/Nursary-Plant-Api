package com.nursery.login.service;

import com.nursery.exceptions.CustomerException;
import com.nursery.login.model.CustomerLogin;

public interface CustomerLoginService {

	public String Customerlogin(CustomerLogin al) throws CustomerException;
	
	public String Customerlogout(String key) throws CustomerException;

}
