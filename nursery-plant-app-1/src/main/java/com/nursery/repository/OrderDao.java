package com.nursery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursery.model.Orders;

@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {

}
