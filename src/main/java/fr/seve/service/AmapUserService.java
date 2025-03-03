package fr.seve.service;

import fr.seve.entities.AmapUser;

public interface AmapUserService {
    AmapUser create(AmapUser amapUser);
    AmapUser update(AmapUser amapUser);
    void delete(Long id);
    AmapUser findById(Long id);
}
