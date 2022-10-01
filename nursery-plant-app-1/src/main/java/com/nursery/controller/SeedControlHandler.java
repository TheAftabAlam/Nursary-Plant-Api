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

import com.nursery.exceptions.SeedException;
import com.nursery.model.Seed;
import com.nursery.service.SeedService;

@RestController
public class SeedControlHandler {

	@Autowired
	private SeedService Seedservice;

	@PostMapping("/admin/addseed")
	public  ResponseEntity<Seed> addSeed(@RequestBody Seed Seed) throws SeedException{ 
		Seed seed1=	Seedservice.addSeed(Seed);

	return new ResponseEntity<Seed>(seed1,HttpStatus.OK);
	}
	
	@PutMapping("admin/updateseed")
	public ResponseEntity<Seed> UpdateSeed(@RequestBody Seed seed) throws SeedException{
		Seed seed1=	Seedservice.updateSeed(seed);

		return new ResponseEntity<Seed>(seed1,HttpStatus.OK);
	}
	
	
	@DeleteMapping("admin/deleteseed")//for deleteing seed we don't need seed we just need planter id
	public ResponseEntity<Seed> DeleteSeed(@RequestBody Seed seed) throws SeedException{
		Seed seed1=Seedservice.deleteSeed(seed);
		return new ResponseEntity<Seed>(seed1,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewseed/{id}")
	public ResponseEntity<Seed> GetSeedById(@PathVariable("id") Integer id) throws SeedException{
		Seed seed1 =Seedservice.viewSeed(id);
		
		return new ResponseEntity<Seed>(seed1,HttpStatus.OK);
	}
	

	@GetMapping("/viewseedsByName/{commonName}")
	public ResponseEntity<List<Seed>> GetPlantcommonName(@PathVariable("commonName") String plantcommonName) throws SeedException{
		List<Seed> seed1= Seedservice.viewSeed(plantcommonName);
		return new ResponseEntity<List<Seed>>(seed1,HttpStatus.OK);
	}
		
	@GetMapping("/viewAllseeds")
	public ResponseEntity<List<Seed>> ShowSeed() throws SeedException{
	List<Seed> seeds= Seedservice.viewAllSeeds();
		return new ResponseEntity<List<Seed>>(seeds,HttpStatus.OK);
	}
	
	
	@GetMapping("/viewseedsByType/{type}")
	public ResponseEntity<List<Seed>> ViewSeedsByType(@PathVariable("type") String type) throws SeedException{
		List<Seed> seed=	Seedservice.viewSeed(type);
		return new ResponseEntity<List<Seed>>(seed,HttpStatus.OK);
	}
}
