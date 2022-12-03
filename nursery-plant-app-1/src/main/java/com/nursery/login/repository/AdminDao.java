package com.nursery.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursery.login.model.Admin;


@Repository
public interface AdminDao extends JpaRepository<Admin, Integer>{

	public Admin findByadminName(String adminName);

}
