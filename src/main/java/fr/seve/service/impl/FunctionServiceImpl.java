package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.Function;
import fr.seve.repository.FunctionRepository;

import fr.seve.service.FunctionService;

@Service
public class FunctionServiceImpl implements FunctionService {
	
	@Autowired 
	private FunctionRepository functionRepository;

	@Override
	public List<Function> findAll() {
		
		return functionRepository.findAll();
	}

	@Override
	public Function findById(Long id) {
		
		return functionRepository.findById(id).orElse(null);
	}

	@Override
	public Function save(Function function) {

		return functionRepository.save(function);
	}

	
}
