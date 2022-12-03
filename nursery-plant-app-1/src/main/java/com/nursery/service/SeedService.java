package com.nursery.service;

import java.util.List;

import com.nursery.exceptions.SeedException;
import com.nursery.model.Seed;

public interface SeedService {

	public Seed addSeed(Seed seed,Integer planterId)throws SeedException;
	public Seed updateSeed(Seed seed)throws SeedException;
	public Seed deleteSeed(Seed seed)throws SeedException;
	public Seed viewSeed(Integer seedId)throws SeedException;
	public List<Seed> viewSeed(String commonName)throws SeedException;
	public List<Seed> viewAllSeeds()throws SeedException ;
	public List<Seed> viewAllSeeds(String typeOfSeed)throws SeedException ;
	
}
