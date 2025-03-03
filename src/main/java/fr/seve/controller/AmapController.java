package fr.seve.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.seve.entities.AMAP;
import fr.seve.entities.Configuration;
import fr.seve.entities.SaasUser;
import fr.seve.service.AmapService;
import fr.seve.service.ConfigurationService;
import fr.seve.service.SaasUserService;

@Controller
@RequestMapping("/amap")
public class AmapController {

	@Autowired
	private AmapService amapService;
	
	@Autowired
	private SaasUserService saasUserService;
	
	@GetMapping
	public ModelAndView listAmaps(Model model) {
		List<AMAP> amaps = amapService.findAll();

		model.addAttribute("amaps", amaps);

		ModelAndView mv = new ModelAndView("amap-list");
        mv.addObject("css", "/resources/css/saas/home.css");
        return mv;
	}
	
	@Secured({"ROLE_SAAS_CUSTOM"})
	@GetMapping("{id}")
	public String getAmap(@PathVariable Long id, Model model) {
		
		AMAP amap = amapService.findById(id);
		model.addAttribute("amap", amap);
		return "amap-details";
		
	}
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("amap", new AMAP());
		return "amap-form";
	}
	
	
	@PostMapping("add")
	public String saveAmap(@ModelAttribute AMAP amap) {
		amapService.save(amap);
		return "redirect:/amap";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteAdherent(@PathVariable Long id) {
		amapService.deleteById(id);
		return "redirect:/amap";
		
	}
	@Secured({"ROLE_SAAS_CUSTOM"})
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AMAP amap = amapService.findById(id);
        model.addAttribute("amap", amap);
        return "amap-form";
    }

	@Secured({"ROLE_SAAS_CUSTOM"})
    @PostMapping("/edit/{id}")
    public String updateAmap(@ModelAttribute AMAP amap) {
        amapService.save(amap);
        return "redirect:/amap";
    }
    
	@Secured({"ROLE_SAAS_CUSTOM"})
	@GetMapping("/info/{id}")
	public ModelAndView configAmap(@PathVariable Long id, Model model) {
	    SaasUser user = saasUserService.findById(id);
	    AMAP amap = user.getAmap();
	    model.addAttribute("amap", amap);
		ModelAndView mv = new ModelAndView("saas-account-config-amap");
        mv.addObject("css", "/resources/css/saas/config.css");
        return mv;
		}
	
	@Secured({"ROLE_SAAS_CUSTOM"})
	@GetMapping("/info")
	public ModelAndView configContent(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
        	return new ModelAndView("redirect:/login"); // Redirige vers la page de connexion si non authentifié
        }
        
        String email = authentication.getName(); 
        SaasUser saasUser = saasUserService.findByEmail(email);

	    AMAP amap = saasUser.getAmap();
	    model.addAttribute("amap", amap);
		ModelAndView mv = new ModelAndView("saas-account-config-amap");
        mv.addObject("css", "/resources/css/saas/config.css");
        return mv;
	}
	
	@Secured({"ROLE_SAAS_CUSTOM"})
	@PostMapping("editAmap/{id}")
	public String editDesign(@PathVariable Long id, AMAP amap, RedirectAttributes redirectAttributes) {
		AMAP newAmap = amapService.findById(id);
		newAmap.setName(amap.getName());
		newAmap.setAddress(amap.getAddress());
		newAmap.setSiret(amap.getSiret());
		newAmap.setMembershipFee(amap.getMembershipFee());
		amapService.save(newAmap);

		redirectAttributes.addFlashAttribute("message", "Les informations ont bien été enregistrées.");
		return  "redirect:/amap/info/{id}";
	}

}