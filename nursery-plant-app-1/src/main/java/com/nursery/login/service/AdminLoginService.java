package com.nursery.login.service;

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.AdminLogin;

public interface AdminLoginService {
	
	public String Adminlogin(AdminLogin al) throws AdminException;
	
	public String Adminlogout(String key) throws AdminException;

}
