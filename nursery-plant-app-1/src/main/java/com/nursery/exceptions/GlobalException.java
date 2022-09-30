package com.nursery.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> OrderExceptionHandler(OrderException oe, WebRequest wr)
	{
		MyErrorDetails myErrorDetails=new MyErrorDetails();
		myErrorDetails.setLocalDate(LocalDate.now());
		myErrorDetails.setDetail(wr.getDescription(false));
		myErrorDetails.setMessage(oe.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
		
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception oe, WebRequest wr)
	{
		MyErrorDetails myErrorDetails=new MyErrorDetails();
		myErrorDetails.setLocalDate(LocalDate.now());
		myErrorDetails.setDetail(wr.getDescription(false));
		myErrorDetails.setMessage(oe.getMessage());
		
		return new ResponseEntity<MyErrorDetails>(myErrorDetails,HttpStatus.BAD_REQUEST);
		
		
	}
}
