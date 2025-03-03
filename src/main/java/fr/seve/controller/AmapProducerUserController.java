package fr.seve.controller;

import fr.seve.entities.AMAP;
import fr.seve.entities.AmapIndividualUser;
import fr.seve.entities.AmapProducerUser;
import fr.seve.entities.AmapSpace;
import fr.seve.entities.AmapUser;
import fr.seve.entities.Product;
import fr.seve.entities.enums.AmapUserRole;
import fr.seve.entities.enums.AmapUserType;
import fr.seve.service.AmapIndividualUserService;
import fr.seve.service.AmapProducerUserService;
import fr.seve.service.ProductService;
import fr.seve.service.impl.ProductServiceImpl;
import fr.seve.utils.AmapUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/{slug}/producer")
public class AmapProducerUserController {

    @Autowired
    private AmapProducerUserService amapProducerUserService;
    
    
    /**
     * Afficher le formulaire de création de compte.
     */
    @GetMapping("/signup")
    public String showSignupForm(HttpServletRequest request, Model model) {

    	model.addAttribute("slug", AmapUtils.getAmapFromRequest(request).getSlug());
        model.addAttribute("amapProducerUser", new AmapProducerUser());
        return "amap-producer-signup";
    }

    /**
     * Traiter le formulaire de création de compte.
     */
    @PostMapping("/signup")
    public String processSignupForm(
            @Valid @ModelAttribute("amapProducerUser") AmapProducerUser amapProducerUser,
            BindingResult result,
            HttpServletRequest request,
            Model model) {
    	AMAP amap = AmapUtils.getAmapFromRequest(request);
    	String slug = amap.getSlug();
    	model.addAttribute("slug",slug);
    	amapProducerUser.getAmapUser().setRole(AmapUserRole.AMAP_USER);
    	amapProducerUser.getAmapUser().setType(AmapUserType.PRODUCER);
        amapProducerUser.getAmapUser().setAmapSpace(amap.getAmapSpace());

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error));
            model.addAttribute("signupError", "Veuillez corriger les erreurs dans le formulaire.");
            return "amap-producer-signup";
        }

        try {
            amapProducerUserService.createProducerUser(amapProducerUser);
            return "redirect:/{slug}/login";
        } catch (Exception e) {
            model.addAttribute("signupError", "Une erreur s'est produite lors de la création du compte.");
            return "amap-producer-signup";
        }
    }
    
    
    @GetMapping("/update")
    public String showUpdateForm(@ModelAttribute("amapUser") AmapUser amapUser, Model model) {

        model.addAttribute("amapProducerUser", amapUser.getProducerUser());
        return "amap-producer-signup";
    }
    
 
    @PostMapping("/update")
    public String updateUser(
            @Valid @ModelAttribute("amapProducerUser") AmapProducerUser updatedProducerUser,
            BindingResult result,
            HttpServletRequest request,
            Model model) {
        // Récupérer l'AMAP et ajouter le slug au modèle
        AMAP amap = AmapUtils.getAmapFromRequest(request);
        String slug = amap.getSlug();
        model.addAttribute("slug", slug);

        // Vérifier les erreurs de validation et retourner les messages d'erreur au formulaire
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error));
            model.addAttribute("signupError", "Veuillez corriger les erreurs dans le formulaire.");
            return "amap-producer-signup";
        }

        try {
            // Récupérer l'utilisateur producteur existant à partir de l'ID
            AmapProducerUser existingProducerUser = amapProducerUserService.findById(updatedProducerUser.getId());
            if (existingProducerUser == null) {
                model.addAttribute("signupError", "Utilisateur producteur introuvable.");
                return "amap-producer-signup";
            }

            // Mettre à jour les champs modifiables
            existingProducerUser.setRib(updatedProducerUser.getRib());
            existingProducerUser.setAddress(updatedProducerUser.getAddress());

            // Mise à jour des champs de l'objet `AmapUser` associé
            AmapUser existingAmapUser = existingProducerUser.getAmapUser();
            AmapUser updatedAmapUser = updatedProducerUser.getAmapUser();
            existingAmapUser.setName(updatedAmapUser.getName());
            existingAmapUser.setFirstname(updatedAmapUser.getFirstname());
            existingAmapUser.setEmail(updatedAmapUser.getEmail());
            existingAmapUser.setPhone(updatedAmapUser.getPhone());

            // Sauvegarder les modifications via le service
            amapProducerUserService.updateProducerUser(existingProducerUser);

            // Redirection vers le tableau de bord après succès
            return "redirect:/" + slug + "/dashboard";
        } catch (Exception e) {
            // Afficher l'exception dans les logs pour débogage
            e.printStackTrace();

            // Ajouter un message d'erreur global au modèle
            model.addAttribute("signupError", "Une erreur inattendue s'est produite. Veuillez réessayer.");
            return "amap-producer-signup";
        }
    }

}
