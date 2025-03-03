package fr.seve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.seve.entities.AMAP;

@Repository
public interface AmapRepository extends JpaRepository<AMAP, Long>{

	AMAP findBySlug(String slug);
	
	Long findSubscriptionIdBySlug(String slug);
	
}