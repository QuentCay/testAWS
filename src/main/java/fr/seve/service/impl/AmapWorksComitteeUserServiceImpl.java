package fr.seve.service.impl;

import fr.seve.entities.AmapUser;
import fr.seve.entities.AmapWorksComitteeUser;
import fr.seve.repository.AmapUserRepository;
import fr.seve.repository.AmapWorksComitteeUserRepository;
import fr.seve.service.AmapWorksComitteeUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AmapWorksComitteeUserServiceImpl implements AmapWorksComitteeUserService {

    @Autowired
    private AmapWorksComitteeUserRepository amapWorksComitteeUserRepository;

    @Autowired
    private AmapUserRepository amapUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public AmapWorksComitteeUser createWorksComitteeUser(AmapWorksComitteeUser amapWorksComitteeUser) {


    	// Créer l'AmapUser
        AmapUser amapUser = new AmapUser();
        amapUser.setFirstname(amapWorksComitteeUser.getAmapUser().getFirstname());
        amapUser.setName(amapWorksComitteeUser.getAmapUser().getName());
        amapUser.setEmail(amapWorksComitteeUser.getAmapUser().getEmail());
        amapUser.setPhone(amapWorksComitteeUser.getAmapUser().getPhone());
        amapUser.setPassword(passwordEncoder.encode(amapWorksComitteeUser.getAmapUser().getPassword()));
        amapUser.setRole(amapWorksComitteeUser.getAmapUser().getRole());
        amapUser.setType(amapWorksComitteeUser.getAmapUser().getType());
        amapUser.setCreateDate(LocalDateTime.now().toString());
        amapUser.setLastModifyDate(LocalDateTime.now().toString());
        amapUser.setAmapSpace(amapWorksComitteeUser.getAmapUser().getAmapSpace());

        // Sauvegarder l'AmapUser
        AmapUser savedAmapUser = amapUserRepository.save(amapUser);

        // Lier l'WorksComitteeUser à l'AmapUser sauvegardé
        amapWorksComitteeUser.setAmapUser(savedAmapUser);

        // Sauvegarder l'WorksComitteeUser
        return amapWorksComitteeUserRepository.save(amapWorksComitteeUser);
    }

    @Override
    public AmapWorksComitteeUser updateWorksComitteeUser(AmapWorksComitteeUser amapWorksComitteeUser) {
        amapUserRepository.save(amapWorksComitteeUser.getAmapUser());
        return amapWorksComitteeUserRepository.save(amapWorksComitteeUser);
    }

    @Override
    public void deleteWorksComitteeUser(Long id) {
        // Supprimer l'WorksComitteeUser et l'AmapUser en cascade
        amapWorksComitteeUserRepository.deleteById(id);
    }

    @Override
    public List<AmapWorksComitteeUser> findByAmapSpaceId(Long amapSpaceId) {
        return amapWorksComitteeUserRepository.findByAmapSpaceId(amapSpaceId);
    }

    @Override
    public AmapWorksComitteeUser findById(Long id) {
        return amapWorksComitteeUserRepository.findById(id).orElse(null);
    }

}
