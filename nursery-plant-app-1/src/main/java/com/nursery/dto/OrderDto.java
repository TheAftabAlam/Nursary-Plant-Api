package com.nursery.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.nursery.model.ShoppingCart;

import lombok.Data;

@Data
public class OrderDto {

	@NotNull
    private String orderDescription;
	@NotNull
    private List<ShoppingCart> cartItems;
	@NotNull
    private String customerEmail;
	@NotNull
    private String customerName;
    
}
