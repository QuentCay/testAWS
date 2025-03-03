package fr.seve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.seve.entities.AmapSpace;
import fr.seve.entities.AmapUser;
import fr.seve.entities.enums.AmapUserType;


public interface AmapUserRepository extends JpaRepository<AmapUser, Long>{

	Optional<AmapUser> findByEmail(String email);
    Optional<AmapUser> findByType(AmapUserType type);
    Optional<AmapUser> findByAmapSpace(AmapSpace amapSpace);
    Optional<AmapUser> findByEmailAndAmapSpace(String email, AmapSpace amapSpace);
    
}
