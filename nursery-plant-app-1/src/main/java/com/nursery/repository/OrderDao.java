package com.nursery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nursery.model.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {

}
