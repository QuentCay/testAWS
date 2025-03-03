package fr.seve.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.seve.entities.SaasUser;
import fr.seve.service.SaasUserService;
import fr.seve.service.impl.AmapUserDetailsService;


@Controller
@RequestMapping("/{slug}")
public class AmapLoginController {

	    @Autowired
	    private AmapUserDetailsService userDetailsService;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @GetMapping("/login")
	    public ModelAndView login(Model model, @RequestParam(value = "error", required = false) String error) {
	    	if (error != null) {
	    		model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
	    	}
	    	ModelAndView mv = new ModelAndView("amap-login");
	    	return mv;
	    }

	    @PostMapping("/login")
	    public String login(@PathVariable String slug, @RequestParam String username, @RequestParam String password, HttpServletRequest request) {
	        try {
	            // Charger l'utilisateur via UserDetailsService
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	            // Vérifier le mot de passe
	            if (passwordEncoder.matches(password, userDetails.getPassword())) {
	                // Créer une instance d'authentification
	                UsernamePasswordAuthenticationToken authenticationToken =
	                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

	                // Ajouter l'authentification dans le contexte de sécurité
	                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

	                // Ajouter l'utilisateur à la session si nécessaire
	                request.getSession().setAttribute("user", userDetails);

	                // Redirection après authentification
	                return "redirect:/" + slug;
	            } else {
	                // Mot de passe incorrect
	                return "redirect:/" + slug + "/login?error";
	            }
	        } catch (UsernameNotFoundException e) {
	            // Utilisateur non trouvé
	            return "redirect:/" + slug + "/login?error";
	        }
	    }

		 
		 @GetMapping("/logout")
		 public String logout(@PathVariable String slug, HttpServletRequest request) {
		     // Supprimer l'authentification du contexte de sécurité
		     SecurityContextHolder.clearContext();

		     // Invalider la session HTTP pour supprimer toutes les données utilisateur
		     if (request.getSession(false) != null) {
		         request.getSession().invalidate();
		     }

		     // Rediriger vers la page d'accueil ou une autre page après la déconnexion
		     return "redirect:/" + slug + "/login?logout";
		 }

	}
