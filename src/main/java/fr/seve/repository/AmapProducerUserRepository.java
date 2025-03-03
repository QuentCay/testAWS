package fr.seve.repository;

import fr.seve.entities.AmapProducerUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AmapProducerUserRepository extends JpaRepository<AmapProducerUser, Long> {

	@Query("SELECT apu FROM AmapProducerUser apu JOIN apu.amapUser au WHERE au.amapSpace.id = :amapSpaceId")
    List<AmapProducerUser> findByAmapSpaceId(Long amapSpaceId);
}