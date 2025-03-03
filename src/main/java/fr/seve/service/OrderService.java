package fr.seve.service;

import java.util.List;

import fr.seve.entities.Order;

public interface OrderService {

	List<Order> findAll();

	Order save(Order order);

	Order findById(Long id);

	void deleteById(Long Id);
	
	List<Order> findByUserId(Long id);
		
}
