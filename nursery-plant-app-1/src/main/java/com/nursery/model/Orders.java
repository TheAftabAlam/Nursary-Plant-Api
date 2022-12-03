package com.nursery.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingOrderId;
	
//	
//	
//	private LocalDate orderDate;
//	
//	@NotBlank(message = "Transaction cannot be blank")
//	private String transactionMode;
//	
//	@NotNull
//	@Min(value = 1, message = "cannot be 0")
//	private Integer quantity;
//	
//	@Min(value = 1, message = "cannot be 0")
//	private double totalCost;
//	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
//	private Planter planter;
//	
	
//	
//	 @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCart.class)
//	 @JoinColumn(name = "booking_order_id", referencedColumnName = "id")
//	 private List<ShoppingCart> cartItems;
	
	 private String orderDescription;

	    @OneToOne(cascade = CascadeType.MERGE)
	    private Customer customer;


	    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCart.class)
	    private List<ShoppingCart> cartItems;


		public Orders(String orderDescription, Customer customer, List<ShoppingCart> cartItems) {
			super();
			this.orderDescription = orderDescription;
			this.customer = customer;
			this.cartItems = cartItems;
		}
	
	 
	
}
