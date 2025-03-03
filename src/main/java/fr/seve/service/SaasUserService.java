package fr.seve.service;

import java.util.List;

import fr.seve.entities.SaasUser;

public interface SaasUserService {
	
	List<SaasUser> findAll();
	
	SaasUser save(SaasUser saasUser);
	SaasUser findByEmail(String email);
	SaasUser findById (Long id);
	
	void deleteById(Long Id);
	
}
