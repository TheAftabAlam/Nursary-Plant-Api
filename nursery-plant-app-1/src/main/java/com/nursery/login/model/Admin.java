package com.nursery.login.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@NotNull
	@Pattern(regexp = "[a-z]{5,10}",message = "Username must be in alphabetic lowercase with length 5-10")
	private String adminName;
	@NotNull
	@Pattern(regexp = "[a-zA-Z0-9]{6,10}",message = "Password must be in 6 to 10 characters it must have alphbetic with Upper and Lowercase and min 1 Numaric")
	private String password;
	@NotNull
	@Pattern(regexp = "[0-9]{10}",message = "Mobile No must have 10 digits")
	private String mobileNo;
	@NotNull
	@Email
	private String email;
}
