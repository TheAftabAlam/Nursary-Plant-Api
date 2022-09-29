package com.nursery.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.CustomerException;
import com.nursery.login.model.CustomerLogin;
import com.nursery.login.service.CustomerLoginService;

@RestController
public class CustomerrLoginControlHandler {
	@Autowired
	private CustomerLoginService userLoginservice;

	@PostMapping("/user/login")
	public String userLoginHandler(@RequestBody CustomerLogin al) throws CustomerException {
		return userLoginservice.Customerlogin(al);
		 
	}
	@DeleteMapping("/user/logout")
	public String userLogoutHandler(@RequestParam String key )throws  CustomerException {
	return userLoginservice.Customerlogout(key);
		
	}
}
