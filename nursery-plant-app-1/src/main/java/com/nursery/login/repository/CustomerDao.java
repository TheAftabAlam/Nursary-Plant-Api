package com.nursery.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nursery.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
 
	public Customer findBycustomerName(String customerName);

	@Query(value="select * from Customer where customer_name=:customerName and customer_email=:customerEmail",nativeQuery = true)
	public Customer getCustomerBycustomerEmailAndcustomerName(@Param("customerEmail") String customerEmail,@Param("customerName") String customerName);

}
