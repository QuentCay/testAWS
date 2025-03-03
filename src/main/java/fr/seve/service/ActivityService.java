package fr.seve.service;

import java.util.List;

import fr.seve.entities.Activity;
import fr.seve.entities.Product;

public interface ActivityService {
	
	List<Activity> findAll(); 
	
	List<Activity> findByAmapSpaceId(Long amapSpaceId);
	
	Activity save(Activity activity); 
	
	Activity findById(Long id); 
	
	void deletebyId(Long Id); 

	List<Activity> findByProducerId(Long id);
}
