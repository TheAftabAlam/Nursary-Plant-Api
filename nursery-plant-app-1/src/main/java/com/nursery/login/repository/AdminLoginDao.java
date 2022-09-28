package com.nursery.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursery.login.model.AdminLogin;
@Repository
public interface AdminLoginDao extends JpaRepository<AdminLogin, Integer>{

}
