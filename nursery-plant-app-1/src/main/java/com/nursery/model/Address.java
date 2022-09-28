package com.nursery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressID;
	
	@NotEmpty(message = "House No. not be emplty")
	private String houseNo;
	
	@NotEmpty(message = "Colony not be null")
	private String colony;
	
	@NotEmpty(message = "Colony not be null")
	private String city;
	
	@NotEmpty(message = "Colony not be null")
	private String state;
	
	@NotEmpty(message = "Colony not be null")
	private Integer pinCode;
	
	
}
