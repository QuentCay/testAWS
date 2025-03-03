package fr.seve.service.impl;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fr.seve.entities.AMAP;
import fr.seve.entities.AmapUser;
import fr.seve.entities.CustomUser;
import fr.seve.repository.AmapUserRepository;
import fr.seve.utils.AmapUtils;

@Service
public class AmapUserDetailsService implements UserDetailsService {

    @Autowired
    private AmapUserRepository amapUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	AMAP amap = AmapUtils.getAmapFromRequest(request);
    	
        AmapUser amapUser = amapUserRepository.findByEmailAndAmapSpace(email, amap.getAmapSpace())
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur AMAP non trouvé"));

        return new CustomUser(amapUser.getEmail(), amapUser.getPassword(),
        		formatRoleForSecurity(amapUser.getRole().name()), amap.getSlug()); // Appel de la méthode pour formater le rôle
    }

    // Nouvelle fonction pour formater le rôle de l'utilisateur (avec un seul rôle)
    private Collection<? extends GrantedAuthority> formatRoleForSecurity(String role) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
    }
}

