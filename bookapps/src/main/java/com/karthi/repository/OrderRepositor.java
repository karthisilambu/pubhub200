package com.karthi.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.karthi.model.Order;
public interface OrderRepositor extends JpaRepository<Order, Integer> {
	
	public List<Order> findByOrderByIdDesc(Long orderId);
	
	public List<Order> findByUserIdOrderByIdDesc(Long userid);
}
