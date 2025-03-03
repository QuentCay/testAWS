package fr.seve.service.impl;

import fr.seve.entities.AmapIndividualUser;
import fr.seve.entities.AmapSpace;
import fr.seve.entities.AmapUser;
import fr.seve.entities.enums.AmapUserRole;
import fr.seve.entities.enums.AmapUserType;
import fr.seve.repository.AmapIndividualUserRepository;
import fr.seve.repository.AmapSpaceRepository;
import fr.seve.repository.AmapUserRepository;
import fr.seve.service.AmapIndividualUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AmapIndividualUserServiceImpl implements AmapIndividualUserService {

    @Autowired
    private AmapIndividualUserRepository amapIndividualUserRepository;

    @Autowired
    private AmapUserRepository amapUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public AmapIndividualUser createIndividualUser(AmapIndividualUser amapIndividualUser) {


    	// Créer l'AmapUser
        AmapUser amapUser = new AmapUser();
        amapUser.setFirstname(amapIndividualUser.getAmapUser().getFirstname());
        amapUser.setName(amapIndividualUser.getAmapUser().getName());
        amapUser.setEmail(amapIndividualUser.getAmapUser().getEmail());
        amapUser.setPhone(amapIndividualUser.getAmapUser().getPhone());
        amapUser.setPassword(passwordEncoder.encode(amapIndividualUser.getAmapUser().getPassword()));
        amapUser.setRole(amapIndividualUser.getAmapUser().getRole());
        amapUser.setType(amapIndividualUser.getAmapUser().getType());
        amapUser.setCreateDate(LocalDateTime.now().toString());
        amapUser.setLastModifyDate(LocalDateTime.now().toString());
        amapUser.setAmapSpace(amapIndividualUser.getAmapUser().getAmapSpace());

        // Sauvegarder l'AmapUser
        AmapUser savedAmapUser = amapUserRepository.save(amapUser);

        // Lier l'IndividualUser à l'AmapUser sauvegardé
        amapIndividualUser.setAmapUser(savedAmapUser);

        // Sauvegarder l'IndividualUser
        return amapIndividualUserRepository.save(amapIndividualUser);
    }

    @Override
    public AmapIndividualUser updateIndividualUser(AmapIndividualUser amapIndividualUser) {
        amapUserRepository.save(amapIndividualUser.getAmapUser());
        return amapIndividualUserRepository.save(amapIndividualUser);
    }

    @Override
    public void deleteIndividualUser(Long id) {
        // Supprimer l'IndividualUser et l'AmapUser en cascade
        amapIndividualUserRepository.deleteById(id);
    }

    @Override
    public List<AmapIndividualUser> findByAmapSpaceId(Long amapSpaceId) {
        return amapIndividualUserRepository.findByAmapSpaceId(amapSpaceId);
    }

    @Override
    public AmapIndividualUser findById(Long id) {
        return amapIndividualUserRepository.findById(id).orElse(null);
    }

}
