package com.nursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.SeedException;
import com.nursery.model.Planter;
import com.nursery.model.Seed;
import com.nursery.repository.PlanterDao;
import com.nursery.repository.SeedDao;

@Service
public class SeedServiceImpl implements SeedService{

	@Autowired
	private SeedDao seedDao;

	@Autowired
	private PlanterDao planterDao;
	
	
	@Override
	public Seed addSeed(Seed seed,Integer PlanterId) throws SeedException {
		
		Optional<Seed> opt= seedDao.findById(seed.getSeedId());
		
		if(opt.isPresent()) {
			throw new SeedException("Seed Already exist");
		
			//here save method will perform as saveOrUpdate based on Id field
		}
		
		Optional<Planter> optional= planterDao.findById(PlanterId);
			
		if(optional.isPresent())
		{
			Planter planter=optional.get();
			planter.getSeeds().add(seed);
			seed.setPlanter(planter);
			return seedDao.save(seed);
		}
		else {
			throw new SeedException("Planter not found");
		}
		
	}

	@Override
	public Seed updateSeed(Seed seed) throws SeedException {
		
		Seed obj = seedDao.findById(seed.getSeedId()).orElseThrow(() -> new SeedException("Seed not found"));
        
		return seedDao.save(seed);
	      
	}

	@Override
	public Seed deleteSeed(Seed seed) throws SeedException {
		Seed existingSeed= seedDao.findById(seed.getSeedId()).orElseThrow(() -> new SeedException("Seed does not exist with Seed Id "+seed.getSeedId()));;
		
		seedDao.delete(existingSeed);
				
		return existingSeed;
	}
	

	@Override
	public Seed viewSeed(Integer seedId) throws SeedException {
		Seed obj= seedDao.findById(seedId).orElseThrow(() -> new SeedException("Seed does not exist with Seed Id "+seedId) );
		
		return obj;
	}

	@Override
	public List<Seed> viewSeed(String commonName) throws SeedException {
		List<Seed> seeds= seedDao.findByCommonName(commonName);
		
		if(seeds.size() > 0)
			return seeds;
		else
			throw new SeedException("Plant does not exist with Name "+commonName);
	}

	@Override
	public List<Seed> viewAllSeeds() throws SeedException {
		List<Seed> seeds= seedDao.findAll();
		
		if(seeds.size() > 0)
			return seeds;
		else
			throw new SeedException("No Seed found..");
	}

	@Override
	public List<Seed> viewAllSeeds(String typeOfSeed) throws SeedException {
		List<Seed> seeds= seedDao.findByTypeOfSeed(typeOfSeed);
		
		if(seeds.size() > 0)
			return seeds;
		else
			throw new SeedException("Seed does not exist with Type "+typeOfSeed);
	}

}
