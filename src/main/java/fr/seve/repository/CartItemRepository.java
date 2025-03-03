package fr.seve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.seve.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
