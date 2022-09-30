package com.nursery.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.PlantException;
import com.nursery.model.Plant;
import com.nursery.service.PlantService;

@RestController
public class PlantControlHandler {
	@Autowired
	private PlantService plantservice;

	@PostMapping("/admin/addplant")
	public  ResponseEntity<Plant> addPlant(@RequestBody Plant plant) throws PlantException{ 
	Plant plant1=	plantservice.addPlant(plant);

	return new ResponseEntity<Plant>(plant1,HttpStatus.OK);
	}
	
	@PutMapping("admin/updateplant")
	public ResponseEntity<Plant> UpdatePlant(@RequestBody Plant plant) throws PlantException{
		Plant plant1=	plantservice.updatePlant(plant);

		return new ResponseEntity<Plant>(plant1,HttpStatus.OK);
	}
	
	
	@DeleteMapping("admin/deleteplant")//for deleteing plant we don't need plant we just need planter id
	public ResponseEntity<Plant> DeletePlant(@RequestBody Plant Plant) throws PlantException{
		Plant plant1=plantservice.deletePlant(Plant);
		return new ResponseEntity<Plant>(plant1,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewplant/{id}")
	public ResponseEntity<Plant> GetPlantById(@PathVariable("id") Integer id) throws PlantException{
		Plant plant1 =plantservice.viewPlant(id);
		
		return new ResponseEntity<Plant>(plant1,HttpStatus.OK);
	}
	

	@GetMapping("/viewplant/{commonName}")
	public ResponseEntity<List<Plant>> GetPlantcommonName(@PathVariable("commonName") String plantcommonName) throws PlantException{
		List<Plant> plant1= plantservice.viewPlant(plantcommonName);
		return new ResponseEntity<List<Plant>>(plant1,HttpStatus.OK);
	}
		
	@GetMapping("/viewplant")
	public ResponseEntity<List<Plant>> ShowPlants() throws PlantException{
	List<Plant> plants=	plantservice.viewAllPlants();
		return new ResponseEntity<List<Plant>>(plants,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewplanters/{type}")
	public ResponseEntity<List<Plant>> ViewPlantsByType(@PathVariable("type") String type) throws PlantException{
		List<Plant> plant=	plantservice.viewPlant(type);
		return new ResponseEntity<List<Plant>>(plant,HttpStatus.OK);
	}
}
