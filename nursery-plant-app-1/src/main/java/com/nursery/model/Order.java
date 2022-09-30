package com.nursery.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingOrderId;
	
	
	private LocalDate orderDate;
	
	@NotBlank(message = "Transaction cannot be blank")
	private String transactionMode;
	
	@NotNull
	@Min(value = 1, message = "cannot be 0")
	private Integer quantity;
	
	@Min(value = 1, message = "cannot be 0")
	private double totalCost;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Planter planter;
	
	
}
