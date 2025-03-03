package fr.seve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.seve.entities.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long>{

}