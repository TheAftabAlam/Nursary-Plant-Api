package com.nursery.service;

import java.util.List;

import com.nursery.exceptions.PlantException;
import com.nursery.model.Plant;
import com.nursery.model.Planter;

public interface PlantService {
	
	public Plant addPlant(Plant plant,Integer planterId)throws PlantException;
	public Plant updatePlant(Plant plant)throws PlantException;
	public Plant deletePlant(Plant plant)throws PlantException;
	public Plant viewPlant(Integer plantId)throws PlantException;
	public List<Plant> viewPlant(String commonName)throws PlantException;
	public List<Plant> viewAllPlants()throws PlantException ;
	public List<Plant> viewAllPlants(String typeOfPlant)throws PlantException ;
	public Planter getPlanterByPlantId(Integer Id) throws PlantException;

}
