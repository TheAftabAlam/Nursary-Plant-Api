package com.nursery.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nursery.login.model.CurrentUserSession;

public interface CurrentUserSessiondDao extends JpaRepository<CurrentUserSession, Integer> {

	public Optional<CurrentUserSession>findByuserId(Integer id);
	public Optional<CurrentUserSession>findByuuid(String uuid);
	
}
