package com.nursery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursery.model.Seed;

@Repository
public interface SeedDao extends JpaRepository<Seed, Integer>{
	

	public List<Seed> findByCommonName(String commonName);
	
	public List<Seed> findByTypeOfSeed(String typeOfSeed);


}
