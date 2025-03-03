package fr.seve.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = "/"; // Par défaut, rediriger vers la page d'accueil du SaaS

        System.out.println("je suis passé par une redirection personnalisée");
        // Vérifier si l'utilisateur appartient à une AMAP
        if (authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_AMAP_USER"))) {
            // Si l'utilisateur est dans une AMAP, rediriger vers la page de l'AMAP
            String slug = (String) request.getAttribute("slug"); // Utiliser l'attribut "amapSlug" (à définir)
            targetUrl = "/" + slug + "/login";  // Redirection vers la page d'accueil de l'AMAP
        }

        // Rediriger l'utilisateur vers la page appropriée
        response.sendRedirect(targetUrl);
    }
}
