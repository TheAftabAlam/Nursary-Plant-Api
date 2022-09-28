package com.nursery.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.AdminLogin;
import com.nursery.login.service.AdminLoginService;

@RestController
public class LoginControlHandler {
	@Autowired
	private AdminLoginService adminLoginservice;

	@PostMapping("/admin/login")
	public String AdminLoginHandler(@RequestBody AdminLogin al) throws AdminException {
		return adminLoginservice.Adminlogin(al);
		
	}
	@DeleteMapping("/admin/logout")
	public String AdminLogoutHandler(@RequestParam String key )throws AdminException {
	return 	adminLoginservice.Adminlogout(key);
		
	}
	
}
