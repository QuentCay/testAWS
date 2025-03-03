package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.seve.entities.AMAP;
import fr.seve.entities.AmapSpace;
import fr.seve.entities.SaasUser;
import fr.seve.repository.SaasUserRepository;
import fr.seve.service.AmapService;
import fr.seve.service.AmapSpaceService;
import fr.seve.service.SaasUserService;

@Service
public class SaasUserServiceImpl implements SaasUserService{

	//Injection de dépendance
	@Autowired 
	private SaasUserRepository saasUserRepository;
	

    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Autowired 
	private AmapService amapService;
	
	@Autowired 
	private AmapSpaceService amapSpaceService;
	

	@Override
	public List<SaasUser> findAll() {
		
		return saasUserRepository.findAll();
	}

	@Override
	public SaasUser save(SaasUser saasUser) {
		saasUser.setPassword(passwordEncoder.encode(saasUser.getPassword()));
		return saasUserRepository.save(saasUser);
	}

	@Override
	public SaasUser findById(Long id) {
		
		return saasUserRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteById(Long id) {
		saasUserRepository.deleteById(id);
	}

	@Override
	public SaasUser findByEmail(String email) {
		return saasUserRepository.findByEmail(email).orElse(null);
	}

}