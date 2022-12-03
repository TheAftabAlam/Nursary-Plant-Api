package com.nursery.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Customer {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerID;
	
	@NotEmpty
	@Size(min = 5, message = "Customer name must be min of 5 characters")
	private String customerName;
	
	@Email(message = "Email is not valid")
	private String customerEmail;
	
	private String role="USER";
	
	@Hidden
	@Size(min = 3, max = 10, message = "Password must be min size of 3 characters and max of 10 characters")
	private String password;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	@JsonIgnore
	private Set<Address> address=new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "customer")
	@JsonIgnore
	private Orders orders;


	public Customer(@NotEmpty @Size(min = 5, message = "Customer name must be min of 5 characters") String customerName,
		@Email(message = "Email is not valid") String customerEmail) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
	}

//	@JsonIgnore
//	public String getPassword() {
//		return password;
//	}
//
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
	
	
	
	
	
	
	
}
