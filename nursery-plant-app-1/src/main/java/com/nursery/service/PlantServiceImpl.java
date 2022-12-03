package com.nursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.PlantException;
import com.nursery.model.Plant;
import com.nursery.model.Planter;
import com.nursery.repository.PlantDao;
import com.nursery.repository.PlanterDao;

@Service
public class PlantServiceImpl implements PlantService{
	
	@Autowired
	private PlantDao plantDao;
	
	@Autowired
	private PlanterDao planterDao;
	

	@Override
	public Plant addPlant(Plant plant,Integer planterID) throws PlantException {
		
		Optional<Plant> opt= plantDao.findById(plant.getPlantId());
		
		if(opt.isPresent()) {
			throw new PlantException("Plant Already existS");
			
			//here save method will perform as saveOrUpdate based on Id field
		}
		
		 Optional<Planter> optional= planterDao.findById(planterID);
		 if(optional.isPresent())
		 {
			 Planter planter=optional.get();
			 planter.getPlants().add(plant);
			 plant.setPlanter(planter);
			 return plantDao.save(plant);
		 }
		 else {
			throw new PlantException("Planter not found");
		}
		
		
		
			
		
		
		
	}

	@Override
	public Plant updatePlant(Plant plant)throws PlantException {
		 Plant obj = plantDao.findById(plant.getPlantId()).orElseThrow(() -> new PlantException("Plant not found "));
	        
		 return plantDao.save(plant);
	       
		
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

	@Override
	public Planter getPlanterByPlantId(Integer pid) throws PlantException {
			Optional<Plant> optional= plantDao.findById(pid);
			if(optional.isPresent())
			{
				Planter planter= optional.get().getPlanter();
				if(planter==null)
				{
					throw new PlantException("Planter not found");
				}else {
					return planter;
				}
			}
			else {
				throw new PlantException("Plant not found");
			}
		
	}

}
