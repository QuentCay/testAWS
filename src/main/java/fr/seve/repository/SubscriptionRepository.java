package fr.seve.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.seve.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
