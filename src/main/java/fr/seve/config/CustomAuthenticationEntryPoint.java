package fr.seve.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Récupérer le slug de l'URL via l'intercepteur pour éviter la 404 si pas d'amap
    	String slug = (String) request.getAttribute("slug");

        String targetLoginPage =  request.getContextPath()+"/login"; // Page de login par défaut (SaaS)
        if (slug != null) {
            targetLoginPage = request.getContextPath()+"/"+ slug + "/login"; // Page de login spécifique à l'AMAP
        }

        // Rediriger vers la page de login appropriée
        response.sendRedirect(targetLoginPage);
    }
}
