package fr.seve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.seve.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
