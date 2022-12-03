package com.nursery.login.model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class AdminLoginDTO {

	private String adminName;
	private String password;
}
