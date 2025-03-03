package fr.seve.service.impl;

import fr.seve.entities.AmapProducerUser;
import fr.seve.entities.AmapUser;
import fr.seve.repository.AmapProducerUserRepository;
import fr.seve.repository.AmapUserRepository;
import fr.seve.service.AmapProducerUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AmapProducerUserServiceImpl implements AmapProducerUserService {

    @Autowired
    private AmapProducerUserRepository amapProducerUserRepository;

    @Autowired
    private AmapUserRepository amapUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public AmapProducerUser createProducerUser(AmapProducerUser amapProducerUser) {


    	// Créer l'AmapUser
        AmapUser amapUser = new AmapUser();
        amapUser.setFirstname(amapProducerUser.getAmapUser().getFirstname());
        amapUser.setName(amapProducerUser.getAmapUser().getName());
        amapUser.setEmail(amapProducerUser.getAmapUser().getEmail());
        amapUser.setPhone(amapProducerUser.getAmapUser().getPhone());
        amapUser.setPassword(passwordEncoder.encode(amapProducerUser.getAmapUser().getPassword()));
        amapUser.setRole(amapProducerUser.getAmapUser().getRole());
        amapUser.setType(amapProducerUser.getAmapUser().getType());
        amapUser.setCreateDate(LocalDateTime.now().toString());
        amapUser.setLastModifyDate(LocalDateTime.now().toString());
        amapUser.setAmapSpace(amapProducerUser.getAmapUser().getAmapSpace());

        // Sauvegarder l'AmapUser
        AmapUser savedAmapUser = amapUserRepository.save(amapUser);

        // Lier l'ProducerUser à l'AmapUser sauvegardé
        amapProducerUser.setAmapUser(savedAmapUser);

        // Sauvegarder l'ProducerUser
        return amapProducerUserRepository.save(amapProducerUser);
    }

    @Override
    public AmapProducerUser updateProducerUser(AmapProducerUser amapProducerUser) {
    	amapUserRepository.save(amapProducerUser.getAmapUser());
        return amapProducerUserRepository.save(amapProducerUser);
    }

    @Override
    public void deleteProducerUser(Long id) {
        // Supprimer l'ProducerUser et l'AmapUser en cascade
        amapProducerUserRepository.deleteById(id);
    }

    @Override
    public List<AmapProducerUser> findByAmapSpaceId(Long amapSpaceId) {
        return amapProducerUserRepository.findByAmapSpaceId(amapSpaceId);
    }

    @Override
    public AmapProducerUser findById(Long id) {
        return amapProducerUserRepository.findById(id).orElse(null);
    }

}
