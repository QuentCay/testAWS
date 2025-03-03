package fr.seve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.seve.entities.AMAP;
import fr.seve.entities.SaasUser;
import fr.seve.service.AmapService;
import fr.seve.service.SaasUserService;


@Controller
public class SaasLoginController {

    @Autowired
    private SaasUserService saasUserService;
    
    @Autowired
    private AmapService amapService;
    
	 @GetMapping("/profile")
	    public ModelAndView profile(Model model) {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
	        	return new ModelAndView("redirect:/login"); // Redirige vers la page de connexion si non authentifié
	        }

	        String email = authentication.getName(); // Récupère le nom d'utilisateur (email)
	        SaasUser saasUser = saasUserService.findByEmail(email); // Récupère le prénom à partir de l'email
	        Long subscriptionId = saasUser.getSubscription().getId();
	        
			Long userId = saasUser.getId();

			AMAP amap = amapService.findById(userId);
	        
	        String subscriptionName = null;

	        if (subscriptionId == 1) {
	        	subscriptionName = "Essentiel";
	        } else if (subscriptionId == 2) {
	        	subscriptionName = "Standard";
	        } else if (subscriptionId == 3) {
	        	subscriptionName = "Premium";
	        }
	        
	        model.addAttribute("subscription", subscriptionName);
	        model.addAttribute("username", saasUser.getFirstname());
	        model.addAttribute("amap", amap);
	        model.addAttribute("slug", amap.getSlug());
	        ModelAndView mv = new ModelAndView("profile");
	        
	        mv.addObject("css", "/resources/css/saas/config.css");
	        mv.addObject("css2", "/resources/css/saas/profile.css");
	        mv.addObject("js", "/resources/js/userCreationForm.js");

	        return mv;
	    }


	 @GetMapping("/login")
	 public ModelAndView login(Model model, @RequestParam(value = "error", required = false) String error) {
	     if (error != null) {
	         model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
	     }
	     ModelAndView mv = new ModelAndView("saasuser-login");
	        
	        mv.addObject("css", "/resources/css/saas/login.css");
	        return mv;
	 }

}