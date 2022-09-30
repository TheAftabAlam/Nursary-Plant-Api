package com.nursery.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
//@EqualsAndHashCode
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressID;
	
	
	@NotEmpty(message = "House No. not be emplty")
	private String houseNo;
	
	@NotEmpty(message = "Colony not be null")
	private String colony;
	
	@NotEmpty(message = "Colony not be null")
	private String city;
	
	@NotEmpty(message = "Colony not be null")
	private String state;
	
	@NotNull(message = "Colony not be null")
	private Integer pinCode;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Customer customer;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(colony, other.colony)
				&& Objects.equals(customer, other.customer) && Objects.equals(houseNo, other.houseNo)
				&& Objects.equals(pinCode, other.pinCode) && Objects.equals(state, other.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, colony, customer, houseNo, pinCode, state);
	}  
	
	
}
