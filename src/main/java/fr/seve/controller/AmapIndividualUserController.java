package fr.seve.controller;

import fr.seve.entities.AMAP;
import fr.seve.entities.AmapIndividualUser;
import fr.seve.entities.AmapSpace;
import fr.seve.entities.AmapUser;
import fr.seve.entities.SaasUser;
import fr.seve.entities.enums.AmapUserRole;
import fr.seve.entities.enums.AmapUserType;
import fr.seve.service.AmapIndividualUserService;
import fr.seve.utils.AmapUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/{slug}/individual")
public class AmapIndividualUserController {

    @Autowired
    private AmapIndividualUserService amapIndividualUserService;

    /**
     * Afficher le formulaire de création de compte.
     */
    @GetMapping("/signup")
    public String showSignupForm(HttpServletRequest request, Model model) {

    	model.addAttribute("slug", AmapUtils.getAmapFromRequest(request).getSlug());
        model.addAttribute("amapIndividualUser", new AmapIndividualUser());
        return "amap-individual-signup";
    }

    /**
     * Traiter le formulaire de création de compte.
     */
    @PostMapping("/signup")
    public String processSignupForm(
            @Valid @ModelAttribute("amapIndividualUser") AmapIndividualUser amapIndividualUser,
            BindingResult result,
            HttpServletRequest request,
            Model model) {
    	AMAP amap = AmapUtils.getAmapFromRequest(request);
    	String slug = amap.getSlug();
    	model.addAttribute("slug",slug);
    	amapIndividualUser.getAmapUser().setRole(AmapUserRole.AMAP_USER);
    	amapIndividualUser.getAmapUser().setType(AmapUserType.INDIVIDUAL);
        amapIndividualUser.getAmapUser().setAmapSpace(amap.getAmapSpace());

        if (result.hasErrors()) {
        	System.out.println( "Au moins une erreur est présente dans le formulaire.");
        	model.addAttribute("signupError", "Au moins une erreur est présente dans le formulaire.");
            return "amap-individual-signup";
        }

        try {
            amapIndividualUserService.createIndividualUser(amapIndividualUser);
            return "redirect:/{slug}/login";
        } catch (Exception e) {
            model.addAttribute("signupError", "Une erreur s'est produite lors de la création du compte.");
            return "amap-individual-signup";
        }
    }
    
    @GetMapping("/update")
    public String showUpdateForm(@ModelAttribute("amapUser") AmapUser amapUser, Model model) {
        model.addAttribute("amapIndividualUser", amapUser.getIndividualUser());
        return "amap-individual-signup"; 
    }

    
    @PostMapping("/update")
    public String updateUser(
            @Valid @ModelAttribute("amapIndividualUser") AmapIndividualUser updatedIndividualUser,
            BindingResult result,
            HttpServletRequest request,
            Model model) {
        AMAP amap = AmapUtils.getAmapFromRequest(request);
        String slug = amap.getSlug();
        model.addAttribute("slug", slug);

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> System.out.println(error));
            model.addAttribute("signupError", "Veuillez corriger les erreurs dans le formulaire.");
            return "amap-individual-signup";
        }

        try {
            AmapIndividualUser existingIndividualUser = amapIndividualUserService.findById(updatedIndividualUser.getId());
            if (existingIndividualUser == null) {
                model.addAttribute("signupError", "Utilisateur individuel introuvable.");
                return "amap-individual-signup";
            }

            existingIndividualUser.setVolunteer(updatedIndividualUser.isVolunteer());

            AmapUser existingAmapUser = existingIndividualUser.getAmapUser();
            AmapUser updatedAmapUser = updatedIndividualUser.getAmapUser();
            existingAmapUser.setName(updatedAmapUser.getName());
            existingAmapUser.setFirstname(updatedAmapUser.getFirstname());
            existingAmapUser.setEmail(updatedAmapUser.getEmail());
            existingAmapUser.setPhone(updatedAmapUser.getPhone());

            amapIndividualUserService.updateIndividualUser(existingIndividualUser);

            return "redirect:/" + slug + "/dashboard";
        } catch (Exception e) {
            e.printStackTrace();

            model.addAttribute("signupError", "Une erreur inattendue s'est produite. Veuillez réessayer.");
            return "amap-individual-signup";
        }
    }


    
    @Secured("ROLE_SAAS_CUSTOM")
    @PostMapping("/createAdmin")
    public String createAdmin(
            @Valid @ModelAttribute("amapIndividualUser") AmapIndividualUser amapIndividualUser,
            BindingResult result,
            @ModelAttribute("globalSaasUser") SaasUser saasUser,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes) {
    	AMAP amap = AmapUtils.getAmapFromRequest(request);
    	String slug = amap.getSlug();
    	if (!slug.equals(saasUser.getAmap().getSlug())) {
    		ObjectError error = new ObjectError("globalError", "Erreur de lien : les slug ne correspondent pas.");
    		result.addError(error);
    		request.getSession().setAttribute("formErrors", result.getAllErrors());
    		return "redirect:/profile";
    	};
        if (result.hasErrors()) {
            request.getSession().setAttribute("formErrors", result.getAllErrors());
            return "redirect:/profile";
        }
        amapIndividualUser.getAmapUser().setRole(AmapUserRole.AMAP_ADMIN);
    	amapIndividualUser.getAmapUser().setType(AmapUserType.INDIVIDUAL);
        amapIndividualUser.getAmapUser().setAmapSpace(amap.getAmapSpace());
        
        try {
            amapIndividualUserService.createIndividualUser(amapIndividualUser);
            redirectAttributes.addFlashAttribute("successMessage", "L'administrateur a été créé avec succès.");
            return "redirect:/profile";
        } catch (Exception e) {
            request.getSession().setAttribute("formErrors", List.of(
                    new ObjectError("globalError", "Une erreur inattendue s'est produite.")
            ));
            return "redirect:/profile";
        }
    }

    
}
