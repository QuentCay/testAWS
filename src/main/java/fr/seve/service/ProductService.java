package fr.seve.service;

import java.util.List;


import fr.seve.entities.Product;

public interface ProductService {
	
	List<Product> findAll();

	Product save(Product product);

	Product findById(Long id);
	
	List<Product> findByProducerId(Long id);

	void deleteById(Long Id);
	
	List<Product> findByAmapSpaceId(Long amapSpaceId);

}
