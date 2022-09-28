package com.nursery.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.Admin;
import com.nursery.login.service.AdminSignupservice;



@RestController
public class SignUpControlHandler {

	@Autowired
	private AdminSignupservice as;
	
	@PostMapping("/admin/signup")
	public Admin CreateAdmin(@RequestBody Admin admin) {
		
		return as.SignupAdmin(admin);
		
	}
	
	@PostMapping("/admin/update")
	public Admin UpdateAdmin(@RequestBody Admin admin,@RequestParam String key) throws AdminException {
		
		return as.UpdateAdmin(admin, key);
	}
}
