package com.nursery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.PlantException;
import com.nursery.model.Plant;
import com.nursery.model.Planter;
import com.nursery.service.PlantService;

@RestController
public class PlantControlHandler {
	@Autowired
	private PlantService plantservice;

	@PostMapping("/admin/addplant/planterID=/{planterId}")
	public  ResponseEntity<Plant> addPlant(@Valid @RequestBody Plant plant,@PathVariable("planterId") Integer id) throws PlantException{ 
	Plant plant1=	plantservice.addPlant(plant,id);

	return new ResponseEntity<Plant>(plant1,HttpStatus.OK);
	}
	
	@PutMapping("admin/updateplant")
	public ResponseEntity<Plant> UpdatePlant(@Valid @RequestBody Plant plant) throws PlantException{
		Plant plant1=	plantservice.updatePlant(plant);

		return new ResponseEntity<Plant>(plant1,HttpStatus.OK);
	}
	
	
	@DeleteMapping("admin/deleteplant")//for deleteing plant we don't need plant we just need planter id
	public ResponseEntity<Plant> DeletePlant(@Valid @RequestBody Plant Plant) throws PlantException{
		Plant plant1=plantservice.deletePlant(Plant);
		return new ResponseEntity<Plant>(plant1,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewplantById/{id}")
	public ResponseEntity<Plant> GetPlantById(@PathVariable("id") Integer id) throws PlantException{
		Plant plant1 =plantservice.viewPlant(id);
		
		return new ResponseEntity<Plant>(plant1,HttpStatus.OK);
	}
	

	@GetMapping("/viewplantByName/{commonName}")
	public ResponseEntity<List<Plant>> GetPlantcommonName(@PathVariable("commonName") String plantcommonName) throws PlantException{
		List<Plant> plant1= plantservice.viewPlant(plantcommonName);
		return new ResponseEntity<List<Plant>>(plant1,HttpStatus.OK);
	}
		
	@GetMapping("/viewplants")
	public ResponseEntity<List<Plant>> ShowPlants() throws PlantException{
	List<Plant> plants=	plantservice.viewAllPlants();
		return new ResponseEntity<List<Plant>>(plants,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewplantsByType/{type}")
	public ResponseEntity<List<Plant>> ViewPlantsByType(@PathVariable("type") String type) throws PlantException{
		List<Plant> plant=	plantservice.viewAllPlants(type);
		return new ResponseEntity<List<Plant>>(plant,HttpStatus.OK);
	}
	

	@GetMapping("/getPlanterByPlantId/{plantId}")
	public ResponseEntity<Planter> getPlanterByPlantId(@PathVariable("plantId") Integer id) throws PlantException{
		Planter planter=	plantservice.getPlanterByPlantId(id);
		return new ResponseEntity<Planter>(planter,HttpStatus.OK);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
