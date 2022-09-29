package com.nursery.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursery.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
 
}
