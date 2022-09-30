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
public class Seed {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer seedId;
	
	@NotEmpty(message = "Common name of seed is missing")
	private String commonName;
	
	@NotEmpty(message = "Bloom time for seed need to mention")
	private String bloomTime;
	
	@NotEmpty(message = "Watering process need to mention")
	private String watering;
	
	@NotEmpty(message = "Difficulty level for seed should be there")
	private String difficultyLevel;
	
	@NotEmpty(message = "Temparature need to mention")
	private String temparature;
	
	@NotEmpty(message = "Seed type is missing")
	private String typeOfSeed;
	
	@NotEmpty(message = "Seed description should not be empty")
	@Size(min = 20, max = 500, message = "Minimun seed description size must be greater than 20 and less than 500")
	private String seedDescription;
	
	@NotNull(message = "Stock size must be there")
	@Min(value = 1, message = "Seed stock must be greate than 0")
	private Integer seedStock;
	
	@NotNull(message = "Seed cost should be there")
	@Min(value = 1, message = "Seed cost must be greate than 0")
	private Double seedCost;
	
	@NotNull(message = "Seed packet not to be empty")
	@Min(value = 1, message = "Minimun 1 seed required in a packet")
	private Integer seedsPerPacket;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Planter planter;
}
