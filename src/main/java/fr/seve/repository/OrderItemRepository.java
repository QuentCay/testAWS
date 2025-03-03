package fr.seve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import fr.seve.entities.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
	@Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId")
	 List<OrderItem> findByOrderId(@Param("orderId")Long orderId);
}

