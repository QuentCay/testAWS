package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.Configuration;
import fr.seve.repository.ConfigurationRepository;
import fr.seve.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
	
	@Autowired
	private ConfigurationRepository configurationRepository;

	@Override
	public List<Configuration> findAll() {
		
		return configurationRepository.findAll();
	}


	@Override
	public Configuration findById(Long id) {
		
		return configurationRepository.findById(id).orElse(null);
	}


	@Override
	public Configuration save(Configuration configuration) {
		return configurationRepository.save(configuration);
	}

}
