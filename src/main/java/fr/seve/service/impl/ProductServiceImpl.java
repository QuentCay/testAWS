package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.Product;
import fr.seve.repository.ProductRepository;
import fr.seve.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository; 

	@Override
	public List<Product> findAll() {
		return productRepository.findAll() ;
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product findById(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long Id) {
		productRepository.deleteById(Id);
		
	}

	@Override
	public List<Product> findByAmapSpaceId(Long amapSpaceId) {
		return productRepository.findByAmapSpaceId(amapSpaceId);
		
	}

	@Override
	public List<Product> findByProducerId(Long id) {
		return productRepository.findByProducerId(id);
	}

}
