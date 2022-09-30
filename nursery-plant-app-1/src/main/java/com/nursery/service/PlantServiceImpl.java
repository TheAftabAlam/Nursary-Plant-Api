package com.nursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.PlantException;
import com.nursery.model.Plant;
import com.nursery.repository.PlantDao;

@Service
public class PlantServiceImpl implements PlantService{
	
	@Autowired
	private PlantDao plantDao;

	@Override
	public Plant addPlant(Plant plant) throws PlantException {
		 Plant obj = plantDao.findById(plant.getPlantId()).orElseThrow(() -> new PlantException("Plant Already exist"));
	        
		 plantDao.save(plant);
	        return plant;
	}

	@Override
	public Plant updatePlant(Plant plant)throws PlantException {
		Optional<Plant> opt= plantDao.findById(plant.getPlantId());
		
		if(opt.isPresent()) {
			
			plantDao.save(plant);
			//here save method will perform as saveOrUpdate based on Id field
		}
		else
			throw new PlantException("Plant not found");
		
		return plant;
		
	}

	@Override
	public Plant deletePlant(Plant plant) throws PlantException {
		Plant existingPlant= plantDao.findById(plant.getPlantId()).orElseThrow(() -> new PlantException("Plant does not exist with Plant Id "+plant.getPlantId()));;
		
		plantDao.delete(existingPlant);
				
		return existingPlant;
	}

	@Override
	public Plant viewPlant(Integer plantId) throws PlantException{
		Plant obj= plantDao.findById(plantId).orElseThrow(() -> new PlantException("Plant does not exist with Plant Id "+plantId) );
	
		return obj;
	}

	@Override
	public List<Plant> viewPlant(String commonName) throws PlantException {

		List<Plant> plants= plantDao.findByCommonName(commonName);
		
		if(plants.size() > 0)
			return plants;
		else
			throw new PlantException("Plant does not exist with Name "+commonName);
		
	}

	@Override
	public List<Plant> viewAllPlants() throws PlantException {
		List<Plant> plants= plantDao.findAll();
		
		if(plants.size() > 0)
			return plants;
		else
			throw new PlantException("No Plant found..");
		
	}

	@Override
	public List<Plant> viewAllPlants(String typeOfPlant) throws PlantException {
		List<Plant> plants= plantDao.findByTypeOfPlant(typeOfPlant);
		
		if(plants.size() > 0)
			return plants;
		else
			throw new PlantException("Plant does not exist with Type "+typeOfPlant);
	}

}
