package com.nursery.login.service;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomNumservice {

	
	public String RandomNumber() {
	Random rnd = new Random();
    int number = rnd.nextInt(999999);  
    return String.format("%06d", number);
		}
}
