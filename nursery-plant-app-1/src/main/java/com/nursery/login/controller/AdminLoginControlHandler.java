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

import com.nursery.exceptions.AdminException;
import com.nursery.login.model.AdminLoginDTO;
import com.nursery.login.service.AdminLoginService;

@RestController
public class AdminLoginControlHandler {
	@Autowired
	private AdminLoginService adminLoginservice;

	@PostMapping("/admin/login")
	public String AdminLoginHandler(@Valid @RequestBody AdminLoginDTO al) throws AdminException {
		return adminLoginservice.Adminlogin(al);
		
	}
	@DeleteMapping("/admin/logout")
	public String AdminLogoutHandler(@RequestParam String key )throws AdminException {
	return 	adminLoginservice.Adminlogout(key);
		
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
