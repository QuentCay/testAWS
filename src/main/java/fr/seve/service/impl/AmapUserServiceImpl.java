package fr.seve.service.impl;

import fr.seve.entities.AmapUser;
import fr.seve.repository.AmapUserRepository;
import fr.seve.service.AmapUserService;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AmapUserServiceImpl implements AmapUserService {

    @Autowired
    private AmapUserRepository amapUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public AmapUser create(AmapUser amapUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AmapUser update(AmapUser amapUser) {
    	
        amapUser.setFirstname(amapUser.getFirstname());
        amapUser.setName(amapUser.getName());
        amapUser.setEmail(amapUser.getEmail());
        amapUser.setLastModifyDate(LocalDateTime.now().toString());
        amapUser.setContributionPaid(amapUser.isContributionPaid());

        return amapUserRepository.save(amapUser);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AmapUser findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
