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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nursery.exceptions.PlanterException;
import com.nursery.model.Planter;
import com.nursery.service.PlanterService;

@RestController
public class PlanterControlHandler {
	@Autowired
	private PlanterService planterservice;

	@PostMapping("/admin/addplanter")
	public  ResponseEntity<Planter> addPlanter(@Valid @RequestBody Planter planter) throws PlanterException{ 
	Planter planter1=	planterservice.addPlanter(planter);

	return new ResponseEntity<>(planter1,HttpStatus.OK);
	}
	
	@PutMapping("admin/updateplanter")
	public ResponseEntity<Planter> UpdatePlanter(@Valid @RequestBody Planter planter) throws PlanterException{
		Planter planter1=	planterservice.updatePlanter(planter);

		return new ResponseEntity<>(planter1,HttpStatus.OK);
	}
	
	
	@DeleteMapping("admin/deleteplanter")//for deleteing planter we don't need planter we just need planter id
	public ResponseEntity<Planter> DeletePlanter(@Valid @RequestBody Planter Planter) throws PlanterException{
		Planter planter1=planterservice.deletePlanter(Planter);
		return new ResponseEntity<Planter>(planter1,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewplanter/{id}")
	public ResponseEntity<Planter> GetPlanterById(@RequestParam("id") Integer id) throws PlanterException{
		Planter planter1 =planterservice.viewPlanter(id);
		
		return new ResponseEntity<Planter>(planter1,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewplantersByShape")
	public ResponseEntity<List<Planter>> GetPlanterByShape(@RequestParam("shape") String planterShape) throws PlanterException{
		List<Planter> planter1= planterservice.viewPlanter(planterShape);
		return new ResponseEntity<List<Planter>>(planter1,HttpStatus.OK);
	}
		
	@GetMapping("/viewAllplanters")
	public ResponseEntity<List<Planter>> ShowPlanters() throws PlanterException{
	List<Planter> planters=	planterservice.viewAllPlanters();
		return new ResponseEntity<List<Planter>>(planters,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewplantersByMinMax/{mincost}/{maxcost}")
	public ResponseEntity<List<Planter>> ViewPlantersByMinMax(@PathVariable("min")double min,@PathVariable("max") double max) throws PlanterException{
		List<Planter> planters=	planterservice.viewAllPlanters(min, max);
		return new ResponseEntity<List<Planter>>(planters,HttpStatus.OK);
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
