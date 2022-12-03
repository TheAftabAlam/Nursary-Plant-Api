package com.nursery.service;

import java.util.List;

import com.nursery.exceptions.CustomerException;
import com.nursery.model.Customer;

public interface CustomerService {
	
	public Customer addCustomer(Customer customer)throws CustomerException;
	public Customer updateCustomer(Customer customer)throws CustomerException;
	public Customer deleteCustomer(Customer customer)throws CustomerException;
	public Customer viewCustomer(Integer customerId)throws CustomerException;

	public List<Customer> viewAllCustomers()throws CustomerException ;
	 public Integer isCustomerPresent(Customer customer) throws Exception;
	
}
