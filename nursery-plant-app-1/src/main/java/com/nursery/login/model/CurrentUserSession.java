package com.nursery.login.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data

public class CurrentUserSession {

	@Id
	private Integer id;
	
	private LocalDateTime  localDateTime;
	private String uuid;
	

	
	
	
	
	
}
