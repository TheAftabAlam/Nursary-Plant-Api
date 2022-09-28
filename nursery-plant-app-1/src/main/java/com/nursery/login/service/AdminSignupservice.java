package com.nursery.login.service;

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.Admin;

public interface AdminSignupservice {
	
	public Admin SignupAdmin(Admin admin);
	
	public Admin UpdateAdmin(Admin admin,String key) throws AdminException;
}
