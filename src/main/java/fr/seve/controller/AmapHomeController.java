package fr.seve.controller;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.seve.entities.AMAP;
import fr.seve.entities.Activity;
import fr.seve.entities.AmapProducerUser;
import fr.seve.entities.AmapUser;
import fr.seve.entities.AmapWorksComitteeUser;
import fr.seve.entities.Box;
import fr.seve.entities.Product;
import fr.seve.entities.enums.AmapUserType;
import fr.seve.service.ActivityService;
import fr.seve.service.AmapProducerUserService;
import fr.seve.service.AmapService;
import fr.seve.service.AmapUserService;
import fr.seve.service.AmapWorksComitteeUserService;
import fr.seve.service.BoxService;
import fr.seve.service.ProductService;
import fr.seve.utils.AmapUtils;


@Controller
public class AmapHomeController {
	
    @Autowired
    private ProductService productService;
    @Autowired
    private BoxService boxService;
    @Autowired
    private ActivityService activityService;
    
    @GetMapping("/{slug}")
    public String handleAmapSlug(@PathVariable String slug, Model model, HttpServletRequest request) {        
    	model.addAttribute("amap", AmapUtils.getAmapFromRequest(request));
    	return "amap-home";
    }
    @GetMapping("/{slug}/contact")
    public String Contact(@PathVariable String slug, Model model, HttpServletRequest request) {        
    	model.addAttribute("amap", AmapUtils.getAmapFromRequest(request));
    	model.addAttribute("js", "/resources/js/contact.js");
    	return "amap-contact";
    }
    
    @Secured({"ROLE_AMAP_USER","ROLE_AMAP_ADMIN","ROLE_AMAP_SUPERVISOR"})
    @GetMapping("/{slug}/dashboard")
    public String myAccount(Model model, HttpServletRequest request) {
    	model.addAttribute("amap", AmapUtils.getAmapFromRequest(request));
    	return "amap-account";
    }
    
    /**
     * Afficher le type de compte à créer
     */
    @GetMapping("/{slug}/signup")
    public String showSignupForm(HttpServletRequest request, Model model) {

    	 List<Map.Entry<String, String>> types = List.of(
    		        new AbstractMap.SimpleEntry<>("individual", "Particulier"),
    		        new AbstractMap.SimpleEntry<>("works-comittee", "Comité d'entreprise"),
    		        new AbstractMap.SimpleEntry<>("producer", "Producteur")
    		    );

    		    model.addAttribute("types", types);
    	model.addAttribute("slug", AmapUtils.getAmapFromRequest(request).getSlug());
        return "amap-signup";
    }
    @Secured({"ROLE_AMAP_USER","ROLE_AMAP_ADMIN","ROLE_AMAP_SUPERVISOR"})
    @GetMapping("/{slug}/myproducts")
    public String showProducts(HttpServletRequest request, Model model, @ModelAttribute("amapUser") AmapUser amapUser) {
    	model.addAttribute("slug", AmapUtils.getAmapFromRequest(request).getSlug());
        if (amapUser == null || (amapUser.getType() != AmapUserType.PRODUCER)) {
        	return "redirect:/{slug}/login";
        }
        List<Product> products = productService.findByProducerId(amapUser.getProducerUser().getId());
        List<Box> boxes = boxService.findByProducerId(amapUser.getProducerUser().getId());
        List<Activity> activities = activityService.findByProducerId(amapUser.getProducerUser().getId());
        model.addAttribute("products", products);
        model.addAttribute("boxes", boxes);
        model.addAttribute("activities", activities);
    	return "amap-producer-view";
    }
}
