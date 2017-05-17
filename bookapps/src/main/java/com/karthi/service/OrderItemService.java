package com.karthi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karthi.model.OrderItem;
import com.karthi.repository.BookRepository;
import com.karthi.repository.OrderItemRepository;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	

	
	public void save(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}

	

}
