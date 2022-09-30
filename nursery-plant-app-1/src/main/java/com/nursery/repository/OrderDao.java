package com.nursery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nursery.model.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {

}
