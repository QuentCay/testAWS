package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.Box;
import fr.seve.entities.Order;
import fr.seve.repository.BoxRepository;
import fr.seve.repository.OrderRepository;
import fr.seve.service.BoxService;
import fr.seve.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> findAll() {
		
		return orderRepository.findAll();
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order findById(Long id) {
		
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long Id) {

		orderRepository.deleteById(Id);
		
	}

	@Override
	public List <Order> findByUserId(Long id) {
		return orderRepository.findByUserId(id);
	}

}
