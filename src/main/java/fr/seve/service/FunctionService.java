package fr.seve.service;

import java.util.List;

import fr.seve.entities.Function;


public interface FunctionService {
	
List<Function> findAll();
	
	Function  findById(Long id);
	
	Function save(Function function);
	

}
