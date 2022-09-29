package com.nursery.login.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;
@Entity
@Data
@ToString
public class CustomerLogin {
	@NotNull
	@Id
	private Integer userId;
	private String customerName;
	private String password;
}
