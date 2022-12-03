package com.nursery.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Planter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer planterId;
	
	@NotNull(message = "Height of the planter must be there")
	@Min(value = 1, message = "Planter height should be greater than zero")
	private Integer planterHeight;
	
	@NotNull(message = "Capacity of planter must be there")
	@Min(value = 0, message = "Capacity must be greater than zero")
	private Integer planterCapacity;
	
	@NotNull(message = "Drinage holes are missing")
	@Min(value = 1, message = "Atleast one drinage required")
	private Integer drinageHoles;
	
	@NotEmpty(message = "Planter color is need")
	private String planterColor;
	
	@NotEmpty(message = "Planter shape is neccessary")
	private String planterShape;
	
	@NotNull(message = "Enter the no of planter stock")
	@Min(value = 0, message = "Planter stock must be greater than zero")
	private Integer planterStock;
	
	@NotNull(message = "Price must be there")
	@Min(value = 0 ,message = "Planter Cost never less than zero")
	private Integer planterCost;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "plantId")
	@JsonIgnore
	private List<Plant> plants = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "seedId")
	@JsonIgnore
	private List<Seed> seeds = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "bookingOrderId")
	@JsonIgnore
	private List<Orders> orders=new ArrayList<>();
}
