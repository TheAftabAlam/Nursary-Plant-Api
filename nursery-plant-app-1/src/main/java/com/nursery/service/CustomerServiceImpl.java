package com.nursery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nursery.exceptions.CustomerException;
import com.nursery.login.repository.CustomerDao;
import com.nursery.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;
	
//	@Autowired
//	private CurrentUserSessiondDao cusd;
//	
//	 boolean isCustomerLogin() {
//		List<CurrentUserSession> list = cusd.findAll();
//		
//		if(list.size()>0) {
//			return true;
//		}
//		
//	}

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		Optional<Customer> opt= customerDao.findById(customer.getCustomerID());
		
		if(opt.isPresent()) {
			
			throw new CustomerException("Customer Already exist");
			//here save method will perform as saveOrUpdate based on Id field
		}
		else
			return customerDao.save(customer);
		
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		
		customerDao.findById(customer.getCustomerID()).orElseThrow(() -> new CustomerException("Customer not found"));
        
		 return customerDao.save(customer);
	      
	}

	@Override
	public Customer deleteCustomer(Customer customer) throws CustomerException {
		Customer existingCustomer= customerDao.findById(customer.getCustomerID()).orElseThrow(() -> new CustomerException("Customer does not exist with Customer Id "+customer.getCustomerID()));;
		
		customerDao.delete(existingCustomer);
				
		return existingCustomer;
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		Customer obj= customerDao.findById(customerId).orElseThrow(() -> new CustomerException("Customer does not exist with Customer Id "+customerId) );
		
		return obj;
	}

	@Override
	public List<Customer> viewAllCustomers() throws CustomerException {
		List<Customer> customers= customerDao.findAll();
		
		if(customers.size() > 0)
			return customers;
		else
			throw new CustomerException("No Customer found..");
	}
	
	@Override
	public Integer isCustomerPresent(Customer customer) throws Exception {
	        Customer customer1 = customerDao.getCustomerBycustomerEmailAndcustomerName(customer.getCustomerEmail(),customer.getCustomerName()) ;
	        if(customer1==null)
	        	throw new Exception("Customer not found");
	       
	        return customer1.getCustomerID();
	    }

}
