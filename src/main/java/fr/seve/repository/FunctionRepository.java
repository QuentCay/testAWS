package fr.seve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.seve.entities.Function;


@Repository
public interface FunctionRepository extends JpaRepository <Function, Long> {

}
