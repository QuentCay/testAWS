package fr.seve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.seve.entities.AmapSpace;

@Repository
public interface AmapSpaceRepository extends JpaRepository<AmapSpace, Long>{

}