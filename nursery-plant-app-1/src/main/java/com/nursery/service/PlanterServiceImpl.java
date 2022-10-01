package com.nursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.PlanterException;
import com.nursery.login.repository.CurrentUserSessiondDao;
import com.nursery.model.Planter;
import com.nursery.repository.PlanterDao;

@Service
public class PlanterServiceImpl implements PlanterService{
	
	@Autowired
	private PlanterDao planterDao;
	
	@Autowired
	private CurrentUserSessiondDao cusd;

	@Override
	public Planter addPlanter(Planter planter) throws PlanterException {
		Optional<Planter> opt= planterDao.findById(planter.getPlanterId());
		
		if(opt.isPresent()) {
			throw new PlanterException("Planter Already exist");
			
			//here save method will perform as saveOrUpdate based on Id field
		}
		else
			return planterDao.save(planter);
		
		
	}

	@Override
	public Planter updatePlanter(Planter planter) throws PlanterException {
		
		Planter obj = planterDao.findById(planter.getPlanterId()).orElseThrow(() -> new PlanterException("Planter  not found"));
        
		return planterDao.save(planter);
	        
	}

	@Override
	public Planter deletePlanter(Planter planter) throws PlanterException {
		Planter existingPlanter= planterDao.findById(planter.getPlanterId()).orElseThrow(() -> new PlanterException("Planter does not exist with Plant Id "+planter.getPlanterId()));;
		
		planterDao.delete(existingPlanter);
				
		return existingPlanter;
	}

	@Override
	public Planter viewPlanter(Integer planterId) throws PlanterException {
		Planter obj= planterDao.findById(planterId).orElseThrow(() -> new PlanterException("Planter does not exist with Plant Id "+planterId) );
		
		return obj;
	}

	@Override
	public List<Planter> viewPlanter(String planterShape) throws PlanterException {

		List<Planter> planters= planterDao.findByPlanterShape(planterShape);
		
		if(planters.size() > 0)
			return planters;
		else
			throw new PlanterException("Planter does not exist with Name "+planterShape);
	}

	@Override
	public List<Planter> viewAllPlanters() throws PlanterException {
		List<Planter> planters= planterDao.findAll();
		
		if(planters.size() > 0)
			return planters;
		else
			throw new PlanterException("No Planters found..");
	}

	@Override
	public List<Planter> viewAllPlanters(Double minCost, Double maxCost) throws PlanterException {
		List<Planter> planters= planterDao.getAllPlanterMinMaxCost(minCost, maxCost);
		
		if(planters.size() > 0)
			return planters;
		else
			throw new PlanterException("No Planters found within "+minCost+" And "+ maxCost);
	}
	
	

}
