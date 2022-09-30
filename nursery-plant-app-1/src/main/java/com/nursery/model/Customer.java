package com.nursery.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@NoArgsConstructor
@Data
@ToString
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerID;
	
	@NotEmpty
	@Size(min = 5, message = "Customer name must be min of 5 characters")
	private String customerName;
	
	@Email(message = "Email is not valid")
	private String customerEmail;
	
	@NotEmpty
	private String userName;
	@Size(min = 3, max = 10, message = "Password must be min size of 3 characters and max of 10 characters")
	private String password;
	
	@NotEmpty
	@OneToMany(cascade = CascadeType.ALL)
	private @Valid Set<Address> address;
	
	
	
	
	
	
	
	
}
