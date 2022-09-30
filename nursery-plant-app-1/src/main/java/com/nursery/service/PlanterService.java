package com.nursery.service;

import java.util.List;

import com.nursery.exceptions.PlanterException;
import com.nursery.model.Planter;

public interface PlanterService {
	public Planter addPlanter(Planter planter)throws PlanterException;
	public Planter updatePlanter(Planter planter)throws PlanterException;
	public Planter deletePlanter(Planter planter)throws PlanterException;
	public Planter viewPlanter(Integer planterId)throws PlanterException;
	public List<Planter> viewPlanter(String planterShape)throws PlanterException;
	public List<Planter> viewAllPlanters()throws PlanterException ;
	public List<Planter> viewAllPlanters(Double minCost, Double maxCost)throws PlanterException ;

}
