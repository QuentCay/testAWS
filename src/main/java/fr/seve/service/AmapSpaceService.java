package fr.seve.service;

import java.util.List;

import fr.seve.entities.AMAP;
import fr.seve.entities.Activity;
import fr.seve.entities.AmapSpace;


public interface AmapSpaceService {
	
	List<AmapSpace> findAll();

	AmapSpace save(AmapSpace amapSpace);

	AmapSpace findById(Long id);
	
	

	void deleteById(Long Id);

}
