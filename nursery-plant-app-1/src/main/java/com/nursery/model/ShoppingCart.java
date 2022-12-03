package com.nursery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class ShoppingCart {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	 	@NotNull
	    private int productId;
	    
	    
	    private String productName;

	    @NotNull
	    private int quantity;
	    private float amount;
	    
//	    @ManyToOne(cascade = CascadeType.ALL)
//	    private Orders orders;
	    
}
