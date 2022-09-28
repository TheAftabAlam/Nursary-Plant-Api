package com.nursery.login.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class AdminLogin {

	@NotNull
	@Id
	private Integer userId;
	private String adminName;
	private String password;
}
