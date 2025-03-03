package fr.seve.repository;

import fr.seve.entities.AmapIndividualUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AmapIndividualUserRepository extends JpaRepository<AmapIndividualUser, Long> {

	   // Requête personnalisée pour récupérer les AmapIndividualUser par amapSpaceId
    @Query("SELECT aiu FROM AmapIndividualUser aiu JOIN aiu.amapUser au WHERE au.amapSpace.id = :amapSpaceId")
    List<AmapIndividualUser> findByAmapSpaceId(Long amapSpaceId);
}
