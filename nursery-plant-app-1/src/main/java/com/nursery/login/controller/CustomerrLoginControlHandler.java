package com.nursery.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.CustomerException;
import com.nursery.login.model.CustomerLoginDTO;
import com.nursery.login.service.CustomerLoginService;

@RestController
public class CustomerrLoginControlHandler {
	@Autowired
	private CustomerLoginService userLoginservice;

	@PostMapping("/user/login")
	public String userLoginHandler(@Valid @RequestBody CustomerLoginDTO al) throws CustomerException {
		return userLoginservice.Customerlogin(al);
		 
	}
	@DeleteMapping("/user/logout")
	public String userLogoutHandler(@RequestParam String key )throws  CustomerException {
	return userLoginservice.Customerlogout(key);
		
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
