package fr.seve.config;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import fr.seve.entities.CustomUser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class AmapSlugValidationFilter extends OncePerRequestFilter {

	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	
    	String currentSlugInterceptorProvided = (String) request.getAttribute("slug");
    	String currentSlug=currentSlugInterceptorProvided;
    	String opath = request.getRequestURI().substring(1);
    	System.out.println("URL : "+opath);
        if (opath.startsWith("seve/")) {
            opath = opath.substring(4);
        }
        final String path = opath;
        
        List<String> excludedPaths = Arrays.asList(
        		"/configuration/**", 
                "/resources/**", 
                "/static/**"
        );
        boolean isExcluded = excludedPaths.stream().anyMatch(pattern -> matchesPattern(path, pattern));
        if (!isExcluded) {
        	
        List<String> excludedCPaths = Arrays.asList(
        		"/", 
        		"/login", 
        		"/amap", 
        		"/home", 
        		"/about",
        		"/error",
        		"/profile", 
        		"/amap/**", 
        		"/saasuser/**", 
        		"/saas", 
        		"/saas/**"
        		);


        boolean isExcludedBis = excludedCPaths.stream().anyMatch(pattern -> matchesPattern(path, pattern));
        if (isExcludedBis) {
        	currentSlug = null;
        	System.out.println("chemin exclu : "+path);
        }else {
        	String pat = opath.substring(1);
        	String[] pathParts = pat.split("/");
        	System.out.println("Le chemin découpé : " + Arrays.toString(pathParts));
        	if (pathParts.length > 0) {
        		currentSlug = pathParts[0];}
        }

        System.out.println("slug intercepte : "+currentSlugInterceptorProvided);
        System.out.println("slug rerecherché : "+currentSlug);
            
        // Vérifier que l'utilisateur est connecté et authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null 
            && authentication.isAuthenticated() 
            && !(authentication instanceof AnonymousAuthenticationToken)) {
        	System.out.println("je suis connecté");
            boolean isAmapUser = authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().startsWith("ROLE_AMAP"));
            
            boolean isSaasUser = authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().startsWith("ROLE_SAAS"));


            System.out.println("slug de la requete recuperer manuellement : "+currentSlug);
            if (isAmapUser) {
            	System.out.println("je suis un utilisateur de l'amap");      	
                String userSlug = (String) ((CustomUser) authentication.getPrincipal()).getSlug();
                System.out.println("le slug de mon amap est : "+userSlug);
                
                if (currentSlug == null || !currentSlug.equals(userSlug)) {
                	System.out.println("je me fais deconnecté de mon amap");
                    logout(request, response);
                }
            }
            if (isSaasUser) {System.out.println("je suis un utilisateur du saas");}
            
            if (isSaasUser && !(currentSlug == null) ) {
          System.out.println("je me fais PAS deconnecté de mon SAAS");  	
            	//logout(request, response);
            }
        } else {System.out.println("je ne suis pas connecté");}
        System.out.println("fin de la vérification");
        }
        // Continuer la chaîne de filtres si tout est valide
        filterChain.doFilter(request, response); // C'est ici que la chaîne de filtres continue
}

	// Méthode de déconnexion
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		// Supprimer l'authentification du contexte de sécurité
		SecurityContextHolder.clearContext();

		// Invalider la session HTTP pour supprimer toutes les données utilisateur
		if (request.getSession(false) != null) {
			request.getSession().invalidate();
		}

		// Supprimer le cookie JSESSIONID, si nécessaire
		Cookie cookie = new Cookie("JSESSIONID", null);
		cookie.setMaxAge(0); // Expire immédiatement
		cookie.setPath("/"); // S'assurer que le cookie est supprimé globalement
		response.addCookie(cookie);
	}

	// Méthode pour vérifier la correspondance d'un chemin avec un pattern
	private static boolean matchesPattern(String path, String pattern) {
		// Remplacer "**" par une regex correspondante
		String regex = pattern.replace("**", ".*");
		return path.matches(regex);
	}
}
