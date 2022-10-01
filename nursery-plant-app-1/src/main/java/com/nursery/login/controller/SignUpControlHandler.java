package com.nursery.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<Admin> CreateAdmin(@RequestBody Admin admin) {
		Admin admin1 = as.signupAdmin(admin);
		
		return new ResponseEntity<Admin>(admin1,HttpStatus.OK);
		
	}
	
	@PutMapping("/admin/update")
	public Admin UpdateAdmin(@RequestBody Admin admin,@RequestParam String key) throws AdminException {
		
		return as.updateAdmin(admin, key);
	}
}
