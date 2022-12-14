package com.nursery.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer plantId;
	
	@NotNull(message = "Height of the plant must be there")
	@Min(value = 1, message = "Plant height should be greater than zero")
	private Integer plantHeight;
	
	@NotEmpty(message = "Plant Spread must be there")
	private String plantSpread;
	
	@NotEmpty(message = "Common name of plant is missing")
	private String commonName;
	
	@NotEmpty(message = "Bloom time for plant need to mention")
	private String bloomTime;
	
	@NotEmpty(message = "Use of plant must be there")
	private String medicinalOrCulinaryUse;
	
	@NotEmpty(message = "Difficulty level for plant should be there")
	private String difficultyLevel;
	
	@NotEmpty(message = "Temparature need to mention")
	private String temparature;
	
	@NotEmpty(message = "Plant type is missing")
	private String typeOfPlant;
	
	@NotEmpty(message = "Plant description should not be empty")
	@Size(min = 20, max = 500, message = "Minimun plant description size must be greater than 20 and less than 500")
	private String plantDescription;
	
	@NotNull(message = "Stock size must be there")
	@Min(value = 1, message = "Plant stock must be greate than 0")
	private Integer plantStock;
	
	@NotNull(message = "Plant cost should be there")
	@Min(value = 1, message = "Plant cost must be greate than 0")
	private Double plantCost;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Planter planter;
}
