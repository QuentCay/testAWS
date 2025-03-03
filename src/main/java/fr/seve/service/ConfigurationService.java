package fr.seve.service;

import java.util.List;

import fr.seve.entities.Configuration;

public interface ConfigurationService {

	List<Configuration> findAll();

	Configuration save(Configuration configuration);

	Configuration findById(Long id);

}
