package com.karthi.service;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthi.model.Order;
import com.karthi.repository.OrderRepositor;
import com.karthi.util.EmailUtil;

@Service
public class OrderService {
	@Autowired
	private OrderRepositor orderRepository;
	
	@Autowired
	private EmailUtil emailUtil;
	
	
			public void save(Order order ) throws Exception {
			        
			        String subject = "Your Order Detials";
			        String body = "Welcome to Book Store you are sucessfully place the order !";
			        
			        emailUtil.send(order.getUser().getEmail(), subject, body);
			        orderRepository.save(order);
			}
	
	public List<Order> findAll(){
		return orderRepository.findAll();
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
