package fr.seve.service;

import java.util.List;

import fr.seve.entities.OrderItem;

public interface OrderItemService {

	List<OrderItem> findAll();

	OrderItem save(OrderItem order);

	OrderItem findById(Long id);

	void deleteById(Long Id);
	
	List<OrderItem> findByOrderId(Long id);
		
}
