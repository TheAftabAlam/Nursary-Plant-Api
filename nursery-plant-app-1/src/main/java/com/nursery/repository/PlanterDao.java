package com.nursery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nursery.model.Planter;

@Repository
public interface PlanterDao extends JpaRepository<Planter, Integer> {


	public List<Planter> findByPlanterShape(String planterShape);
	
	@Query("select p from Planter p where p.planterCost > ?1 AND p.planterCost < ?2")
	public List<Planter> getAllPlanterMinMaxCost(Double MinCost, Double MaxCost);
	
}
