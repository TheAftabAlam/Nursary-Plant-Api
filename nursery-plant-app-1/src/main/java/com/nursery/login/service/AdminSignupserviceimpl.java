package com.nursery.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.Admin;
import com.nursery.login.model.CurrentUserSession;
import com.nursery.login.repository.AdminDao;
import com.nursery.login.repository.CurrentUserSessiondDao;

@Service
public class AdminSignupserviceimpl implements AdminSignupservice{

	@Autowired
	private AdminDao adao;
	
	@Autowired
	private CurrentUserSessiondDao cusd;
	@Override
	public Admin SignupAdmin(Admin admin) {
		
		Admin admin1=adao.save(admin);
		
		return admin1;
	
	}

	@Override
	public Admin UpdateAdmin(Admin admin,String key) throws AdminException{
	CurrentUserSession cu=	cusd.findByuuid(key).orElseThrow(()-> new AdminException("User Not Logged in"));

	return adao.save(admin);
	
	}

}
