package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.Box;
import fr.seve.entities.Product;
import fr.seve.repository.BoxRepository;
import fr.seve.service.BoxService;

@Service
public class BoxServiceImpl implements BoxService{
	
	@Autowired
	private BoxRepository boxRepository;

	@Override
	public List<Box> findAll() {
		
		return boxRepository.findAll();
	}

	@Override
	public Box save(Box box) {
		return boxRepository.save(box);
	}

	@Override
	public Box findById(Long id) {
		
		return boxRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long Id) {

		boxRepository.deleteById(Id);
		
	}

	@Override
	public List<Box> findByAmapSpaceId(Long amapSpaceId) {
		return boxRepository.findByAmapSpaceId(amapSpaceId);
		
	}
	@Override
	public List<Box> findByProducerId(Long id) {
		return boxRepository.findByProducerId(id);
	}

}
