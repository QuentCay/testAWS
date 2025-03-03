package fr.seve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import fr.seve.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	
	@Query("SELECT p FROM Product p WHERE p.amapSpace.id = :amapSpaceId")
	List<Product> findByAmapSpaceId(@Param("amapSpaceId")Long amapSpaceId);
	@Query("SELECT p FROM Product p WHERE p.amapProducerUser.id = :producerId")
	List<Product> findByProducerId(@Param("producerId")Long producerId);
	
}
