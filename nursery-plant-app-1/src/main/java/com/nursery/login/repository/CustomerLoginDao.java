package com.nursery.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursery.login.model.CustomerLogin;

@Repository
public interface CustomerLoginDao extends JpaRepository<CustomerLogin, Integer>{

}
