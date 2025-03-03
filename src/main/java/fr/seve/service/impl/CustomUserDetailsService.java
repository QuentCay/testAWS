package fr.seve.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import fr.seve.entities.SaasUser;
import fr.seve.repository.SaasUserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SaasUserRepository saasUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SaasUser saasUser = saasUserRepository.findByEmail(email)
              .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
            saasUser.getEmail(),
            saasUser.getPassword(),
    		formatRoleForSecurity(saasUser.getSaasUserLevel().name())); // Appel de la méthode pour formater le rôle
}

// Nouvelle fonction pour formater le rôle de l'utilisateur (avec un seul rôle)
private Collection<? extends GrantedAuthority> formatRoleForSecurity(String role) {
    return List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
}
}

