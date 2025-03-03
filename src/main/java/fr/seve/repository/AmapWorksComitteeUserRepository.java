package fr.seve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.seve.entities.AmapWorksComitteeUser;

@Repository
public interface AmapWorksComitteeUserRepository extends JpaRepository<AmapWorksComitteeUser, Long> {
	
	@Query("SELECT awcu FROM AmapIndividualUser awcu JOIN awcu.amapUser au WHERE au.amapSpace.id = :amapSpaceId")
    List<AmapWorksComitteeUser> findByAmapSpaceId(Long amapSpaceId);
}
