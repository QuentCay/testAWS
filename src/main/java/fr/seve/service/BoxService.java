package fr.seve.service;

import java.util.List;


import fr.seve.entities.Box;
import fr.seve.entities.Product;


public interface BoxService {
	
	List<Box> findAll();

	Box save(Box box);

	Box findById(Long id);

	void deleteById(Long Id);
	
	List<Box> findByAmapSpaceId(Long amapSpaceId);

	List<Box> findByProducerId(Long id);
}
