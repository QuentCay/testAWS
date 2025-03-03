package fr.seve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.seve.entities.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	 @Query("SELECT a FROM Activity a WHERE a.amapSpace.id = :amapSpaceId")
	List<Activity> findByAmapSpaceId(@Param("amapSpaceId")Long amapSpaceId);
	 @Query("SELECT a FROM Activity a WHERE a.amapProducerUser.id = :producerId")
	 List<Activity> findByProducerId(@Param("producerId")Long producerId);

	 
}
