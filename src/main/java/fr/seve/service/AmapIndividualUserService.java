package fr.seve.service;

import java.util.List;

import fr.seve.entities.AmapIndividualUser;

public interface AmapIndividualUserService {
    AmapIndividualUser createIndividualUser(AmapIndividualUser amapIndividualUser);
    AmapIndividualUser updateIndividualUser(AmapIndividualUser amapIndividualUser);
    void deleteIndividualUser(Long id);
    List<AmapIndividualUser> findByAmapSpaceId(Long amapSpaceId);
	AmapIndividualUser findById(Long id);
}