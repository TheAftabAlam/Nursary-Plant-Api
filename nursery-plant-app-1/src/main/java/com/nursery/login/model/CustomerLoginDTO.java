package com.nursery.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerLoginDTO {
	
	
	private String customerName;
	
	private String password;
}
