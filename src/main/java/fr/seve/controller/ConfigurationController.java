package fr.seve.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.seve.entities.Configuration;
import fr.seve.entities.SaasUser;
import fr.seve.service.ConfigurationService;
import fr.seve.service.SaasUserService;


@Controller
@RequestMapping("/configuration")
public class ConfigurationController {

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private SaasUserService saasUserService;

	@Secured("ROLE_SAAS_CUSTOM")
	@GetMapping
	public String listConfig(Model model) {
		List<Configuration> configurations = configurationService.findAll();
		model.addAttribute("configurations", configurations);
		return "";
	}
	@Secured("ROLE_SAAS_CUSTOM")
	@GetMapping("{id}")
	public String getConfig(@PathVariable Long id, Model model) {
		Configuration configuration = configurationService.findById(id);
		model.addAttribute("configuration", configuration);
		return "";
	}
	
	@Secured("ROLE_SAAS_CUSTOM")
	@GetMapping("/content")
	public ModelAndView configContent(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()
				|| "anonymousUser".equals(authentication.getPrincipal())) {
			return new ModelAndView("redirect:/login");
		}

		String email = authentication.getName();
		SaasUser saasUser = saasUserService.findByEmail(email);
		Long userId = saasUser.getId();

		Configuration configuration = configurationService.findById(userId);
		model.addAttribute("configuration", configuration);
		ModelAndView mv = new ModelAndView("saas-account-config-content");
		mv.addObject("css", "/resources/css/saas/config.css");
		return mv;
	}
	
	@Secured("ROLE_SAAS_CUSTOM")
	@GetMapping("/design")
	public ModelAndView configDesign(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()
				|| "anonymousUser".equals(authentication.getPrincipal())) {
			return new ModelAndView("redirect:/login");
		}

		String email = authentication.getName();
		SaasUser saasUser = saasUserService.findByEmail(email);
		Long userId = saasUser.getId();

		Configuration configuration = configurationService.findById(userId);
		model.addAttribute("configuration", configuration);
		model.addAttribute("polices", Configuration.Police.values());
		ModelAndView mv = new ModelAndView("saas-account-config-design");
		mv.addObject("css", "/resources/css/saas/config.css");
		return mv;
	}

	@Secured("ROLE_SAAS_CUSTOM")
	@PostMapping("editDesign/{id}")
	public String editDesign(@PathVariable Long id, Configuration configuration,
			RedirectAttributes redirectAttributes) {
		Configuration newConf = configurationService.findById(id);
		newConf.setPrimaryColor(configuration.getPrimaryColor());
		newConf.setSecondaryColor(configuration.getSecondaryColor());
		newConf.setTertiaryColor(configuration.getTertiaryColor());
		newConf.setPolice(configuration.getPolice());
		newConf.setIsRoundedBorders(configuration.getIsRoundedBorders());
		configurationService.save(newConf);

		redirectAttributes.addFlashAttribute("message", "Les informations ont bien été enregistrées.");
		return "redirect:/configuration/design";
	}
	
	@Secured("ROLE_SAAS_CUSTOM")
	@PostMapping("editContent/{id}")
	public String editContent(@PathVariable Long id, Configuration configuration,
			@RequestParam("logo") MultipartFile logo, @RequestParam("presentationImage") MultipartFile presentationImage, RedirectAttributes redirectAttributes) {
		
		Configuration newConf = configurationService.findById(id);
		newConf.setPresentationText(configuration.getPresentationText());
		
		if (logo != null && !logo.isEmpty()) {
			try {
				newConf.setLogoData(logo.getBytes());
			} catch (IOException e) {
				redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation du logo.");
				e.printStackTrace();
				return "redirect:/configuration/content";
			}
		}
		
	    if (presentationImage != null && !presentationImage.isEmpty()) {
	        try {
	            newConf.setPresentationImageData(presentationImage.getBytes());
	        } catch (IOException e) {
	            redirectAttributes.addFlashAttribute("message", "Erreur lors de l'importation de l'image de présentation.");
	            e.printStackTrace();
	            return "redirect:/configuration/content";
	        }
	    }
	    
		configurationService.save(newConf);

		redirectAttributes.addFlashAttribute("message", "Les informations ont bien été enregistrées.");
		return "redirect:/configuration/content";
	}

	@GetMapping("/logo/{id}")
	public ResponseEntity<byte[]> getLogo(@PathVariable Long id) {
		Configuration configuration = configurationService.findById(id);
		byte[] logoData = configuration.getLogoData();

		if (logoData != null) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(logoData);
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/presentationImage/{id}")
	public ResponseEntity<byte[]> getPresentationImage(@PathVariable Long id) {
		Configuration configuration = configurationService.findById(id);
		byte[] presentationImageData = configuration.getPresentationImageData();

		if (presentationImageData != null) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(presentationImageData);
		}

		return ResponseEntity.notFound().build();
	}

	// Récupère le css selon l'id de la configuration
	@GetMapping(value = "/css/{id}", produces = "text/css")
	@ResponseBody
	public String getCss(@PathVariable Long id) {
		Configuration configuration = configurationService.findById(id);
		if (configuration == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuration not found");
		}
		return generateCss(configuration);
	}

	// Génère dynamiquement le css en fonction de la configuration
	private String generateCss(Configuration configuration) {
		StringBuilder css = new StringBuilder();

		// Couleurs
		css.append(":root {\n");
		css.append("--primary-color: ").append(configuration.getPrimaryColor()).append(";\n");
		css.append("--secondary-color: ").append(configuration.getSecondaryColor()).append(";\n");
		css.append("--tertiary-color: ").append(configuration.getTertiaryColor()).append(";\n");
		// Bordures
		if (Boolean.TRUE.equals(configuration.getIsRoundedBorders())) {
			css.append("--border-radius: 10px;\n");
		} else {
			css.append("--border-radius: 0px;\n");
		}

		css.append("}\n");

		// Police
		css.append("body {\n");
		css.append("--font-family: '").append(configuration.getPolice().getDisplayName()).append("', sans-serif;\n");
		css.append("}\n");

		return css.toString();
	}
}