package fr.seve.service;

import fr.seve.entities.AmapWorksComitteeUser;
import java.util.List;

public interface AmapWorksComitteeUserService{
    AmapWorksComitteeUser createWorksComitteeUser(AmapWorksComitteeUser amapWorksComitteeUser);
    AmapWorksComitteeUser updateWorksComitteeUser(AmapWorksComitteeUser amapWorksComitteeUser);
    void deleteWorksComitteeUser(Long id);
    List<AmapWorksComitteeUser> findByAmapSpaceId(Long amapSpaceId);
	AmapWorksComitteeUser findById(Long id);
}
