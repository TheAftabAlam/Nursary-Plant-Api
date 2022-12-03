package com.nursery.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.CustomerException;
import com.nursery.login.service.CustomerSignupService;
import com.nursery.model.Customer;

@RestController
public class CustomerSignupControlHandler {

	@Autowired
	private  CustomerSignupService cs;
	
	@PostMapping("/user/signup")
	public Customer CreateAdmin(@Valid @RequestBody Customer customer) {
		 
		return cs.SignupCustomer(customer);
		
	}
	
	@PostMapping("/user/update")
	public Customer UpdateAdmin(@Valid @RequestBody Customer customer,@RequestParam String key) throws CustomerException {
		
		return cs.UpdateCustomer(customer, key);
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
