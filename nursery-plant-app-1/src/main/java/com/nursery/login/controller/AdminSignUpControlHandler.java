package com.nursery.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.Admin;
import com.nursery.login.service.AdminSignupservice;



@RestController
public class AdminSignUpControlHandler {

	@Autowired
	private AdminSignupservice as;
	
	@PostMapping("/admin/signup")
	public ResponseEntity<Admin> CreateAdmin(@Valid @RequestBody Admin admin) {
		Admin admin1 = as.signupAdmin(admin);
		
		return new ResponseEntity<Admin>(admin1,HttpStatus.OK);
		
	}
	
	@PutMapping("/admin/update")
	public Admin UpdateAdmin(@Valid @RequestBody Admin admin,@RequestParam String key) throws AdminException {
		
		return as.updateAdmin(admin, key);
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
