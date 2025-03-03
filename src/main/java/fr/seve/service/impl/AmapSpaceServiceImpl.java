package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.AMAP;
import fr.seve.entities.Activity;
import fr.seve.entities.AmapSpace;
import fr.seve.repository.AmapRepository;
import fr.seve.repository.AmapSpaceRepository;
import fr.seve.service.AmapService;
import fr.seve.service.AmapSpaceService;

@Service
public class AmapSpaceServiceImpl implements AmapSpaceService{
	
	@Autowired
	private AmapSpaceRepository amapSpaceRepository;

	@Override
	public List<AmapSpace> findAll() {
		
		return amapSpaceRepository.findAll();
	}

	@Override
	public AmapSpace save(AmapSpace amapSpace) {
		
		return amapSpaceRepository.save(amapSpace);
	}

	@Override
	public AmapSpace findById(Long id) {
		
		return amapSpaceRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long Id) {

		amapSpaceRepository.deleteById(Id);
		
	}
	

}
