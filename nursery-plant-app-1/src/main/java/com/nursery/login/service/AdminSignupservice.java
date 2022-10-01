package com.nursery.login.service;

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.Admin;

public interface AdminSignupservice {
	
	public Admin signupAdmin(Admin admin);
	
	public Admin updateAdmin(Admin admin,String key) throws AdminException;
	
}
