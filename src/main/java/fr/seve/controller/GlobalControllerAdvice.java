package fr.seve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import fr.seve.entities.AmapUser;
import fr.seve.entities.SaasUser;
import fr.seve.repository.AmapUserRepository;
import fr.seve.repository.SaasUserRepository;

@ControllerAdvice
public class GlobalControllerAdvice {

	@Autowired
    private AmapUserRepository amapUserRepository;
	
	@Autowired
    private SaasUserRepository saasUserRepository;
	
	
    @ModelAttribute("amapUser")
    public AmapUser getCurrentAmapUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() 
                && !authentication.getPrincipal().equals("anonymousUser")) {
        	boolean isAmapUser = authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().startsWith("ROLE_AMAP"));
            if (isAmapUser) {
	            AmapUser amapUser = amapUserRepository.findByEmail(authentication.getName())
	                    .orElseThrow(() -> new UsernameNotFoundException("Utilisateur AMAP non trouvé"));
	            return amapUser;
            }
        }
        return null;
    }
    
    
    @ModelAttribute("globalSaasUser")
    public SaasUser getCurrentSaasUser() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication != null && authentication.isAuthenticated() 
    			&& !authentication.getPrincipal().equals("anonymousUser")) {
    		boolean isglobalSaasUser = authentication.getAuthorities().stream()
    				.anyMatch(authority -> authority.getAuthority().startsWith("ROLE_SAAS"));

    		if (isglobalSaasUser) {

    			SaasUser globalSaasUser = saasUserRepository.findByEmail(authentication.getName())
    					.orElseThrow(() -> new UsernameNotFoundException("Utilisateur SAAS non trouvé"));
    			return globalSaasUser;
    		}
    	}
    	return null;
    }

}
