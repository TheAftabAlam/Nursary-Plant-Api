package com.nursery.login.service;

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.AdminLoginDTO;

public interface AdminLoginService {
	
	public String Adminlogin(AdminLoginDTO al) throws AdminException;
	
	public String Adminlogout(String key) throws AdminException;

}
