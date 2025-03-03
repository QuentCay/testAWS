package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.Box;
import fr.seve.entities.Order;
import fr.seve.entities.OrderItem;
import fr.seve.repository.BoxRepository;
import fr.seve.repository.OrderItemRepository;
import fr.seve.repository.OrderRepository;
import fr.seve.service.BoxService;
import fr.seve.service.OrderItemService;
import fr.seve.service.OrderService;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public List<OrderItem> findAll() {
		
		return orderItemRepository.findAll();
	}

	@Override
	public OrderItem save(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

	@Override
	public OrderItem findById(Long id) {
		
		return orderItemRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long Id) {

		orderItemRepository.deleteById(Id);
		
	}

	@Override
	public List<OrderItem> findByOrderId(Long Id) {
	
		return orderItemRepository.findByOrderId(Id);
	}

}
