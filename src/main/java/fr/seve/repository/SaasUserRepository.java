package fr.seve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.seve.entities.SaasUser;

@Repository
public interface SaasUserRepository extends JpaRepository<SaasUser, Long> {

	Optional<SaasUser> findByEmail(String email);
}
