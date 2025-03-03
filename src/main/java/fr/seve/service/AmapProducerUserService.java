package fr.seve.service;

import fr.seve.entities.AmapProducerUser;
import java.util.List;

public interface AmapProducerUserService {
    AmapProducerUser createProducerUser(AmapProducerUser amapProducerUser);
    AmapProducerUser updateProducerUser(AmapProducerUser amapProducerUser);
    void deleteProducerUser(Long id);
    List<AmapProducerUser> findByAmapSpaceId(Long amapSpaceId);
	AmapProducerUser findById(Long id);
}
