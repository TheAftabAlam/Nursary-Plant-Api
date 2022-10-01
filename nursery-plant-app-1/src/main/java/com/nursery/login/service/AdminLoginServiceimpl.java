package com.nursery.login.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.Admin;
import com.nursery.login.model.AdminLogin;
import com.nursery.login.model.CurrentUserSession;
import com.nursery.login.repository.AdminDao;
import com.nursery.login.repository.AdminLoginDao;
import com.nursery.login.repository.CurrentUserSessiondDao;

@Service
public class AdminLoginServiceimpl implements AdminLoginService{
	
	@Autowired
	private AdminDao ad;
	
	@Autowired
	private AdminLoginDao ald;
	
	@Autowired
	private CurrentUserSessiondDao cusd;

	

	@Override
	public String Adminlogout(String key) throws AdminException  {
		
	CurrentUserSession cus=	cusd.findByuuid(key).orElseThrow(()-> new AdminException("Enter correct Key"));

		ald.deleteById(cus.getUserId());
		cusd.delete(cus);	
		return "Logout Successful";
	}



	@Override
	public String Adminlogin(AdminLogin alogin) throws AdminException {
		
		Admin admin= ad.findById(alogin.getUserId()).orElseThrow(()->new AdminException("UseerId not find"));
			
		if(!admin.getAdminName().equals(alogin.getAdminName())) {

			return "Please Enter correct username";
		}
		if(!admin.getPassword().equals(alogin.getPassword())) {

			return "Please Enter Correct Password";
		}
	 Optional<CurrentUserSession> cu=cusd.findByuserId(alogin.getUserId());
		if(cu.isPresent()) {	
			return "User Alredy logged in";
		}
	 
	 	RandomNumservice ran=new RandomNumservice();
			String uuid=ran.RandomNumber();
			
			CurrentUserSession cus= new CurrentUserSession();
			cus.setUserId(alogin.getUserId());
			cus.setNow(LocalDateTime.now());
			cus.setUuid(uuid);
			
			ald.save(alogin);
			CurrentUserSession cs=	cusd.save(cus);
			return cs.toString();
	}

	
}
