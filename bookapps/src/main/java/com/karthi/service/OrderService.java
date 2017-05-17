package com.karthi.service;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthi.model.Order;
import com.karthi.repository.OrderRepositor;

@Service
public class OrderService {
	@Autowired
	private OrderRepositor orderRepository;
	
	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	public void save(Order order) {
		orderRepository.save(order);
	}

	public Order findOne(Integer id){
		return orderRepository.findOne(id);
}
	
	
	public List<Order> findByUserIdOrderByIdDesc(Long userId){
		return orderRepository.findByUserIdOrderByIdDesc(userId);
	}
	
	
	public List<Order> findByOrderByIdDesc(Long orderId) {
		
		return orderRepository.findByOrderByIdDesc(orderId);
	}
	

	public List<Order> findBYUserId(Long id) {
		
		return orderRepository.findByUserIdOrderByIdDesc(id);
	}
}
