package fr.seve.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.seve.entities.AMAP;
import fr.seve.entities.AmapSpace;
import fr.seve.entities.Configuration;
import fr.seve.entities.SaasUser;
import fr.seve.entities.Subscription;
import fr.seve.entities.enums.SaasUserLevel;
import fr.seve.service.AmapSpaceService;
import fr.seve.service.SaasUserService;
import fr.seve.service.SubscriptionService;
import fr.seve.service.impl.SaasUserServiceImpl;

@Controller
@RequestMapping("/saasuser")
public class SaasUserController {

	@Autowired

	private SaasUserService saasUserService;

	@Autowired
	private AmapSpaceService amapSpaceService;

	@Autowired
	private SubscriptionService subscriptionService;

	@ModelAttribute("saasUser")
	public SaasUser setSaasUser() {
		return new SaasUser();
	}

	@GetMapping("/showSignUpForm")
	public ModelAndView showForm() {
		ModelAndView mv = new ModelAndView("saasuser-signup-form");
		mv.addObject("css", "/resources/css/saas/signup-form.css");
		return mv;

	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new SaasUser());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") SaasUser saasUser) {
		saasUserService.save(saasUser); // Appel du service pour sauvegarder l'utilisateur
		return "redirect:/login"; // Redirection vers la page de connexion
	}

	@GetMapping("/subscription-essential")
	public ModelAndView showFormEs() {
		ModelAndView mv = new ModelAndView("saas-signup-es");
		mv.addObject("css", "/resources/css/saas/signup-form.css");
		return mv;
	}

	@GetMapping("/subscription-standard")
	public ModelAndView showFormSt() {
		ModelAndView mv = new ModelAndView("saas-signup-st");
		mv.addObject("css", "/resources/css/saas/signup-form.css");
		return mv;
	}

	@GetMapping("/subscription-premium")
	public ModelAndView showFormPr() {
		ModelAndView mv = new ModelAndView("saas-signup-pr");
		mv.addObject("css", "/resources/css/saas/signup-form.css");
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@PostMapping("/saveSignUpEssential")
	public ModelAndView saveUserSaas(@Valid @ModelAttribute("saasUser") SaasUser saasUser, BindingResult bindingResult,
			Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Vérifie si l'utilisateur n'est pas déjà authentifié
		if (authentication == null || !authentication.isAuthenticated()
				|| "anonymousUser".equals(authentication.getPrincipal())) {

			SaasUser saasUserControl = saasUserService.findByEmail(saasUser.getEmail());
			if (saasUserControl != null){
				ModelAndView mv = new ModelAndView("saas-signup-es");
				List<String> errors = new ArrayList<>();
			    errors.add("Un utilisateur avec cet email existe déjà.");
			    mv.addObject("css", "/resources/css/saas/signup-form.css");
				mv.addObject("saasUser", saasUser); 
				mv.addObject("errorMessages", errors);
				return mv;
			}
			saasUser.setSaasUserLevel(SaasUserLevel.SAAS_CUSTOM);
			saasUser.setCreateDate(LocalDateTime.now().toString());
			saasUser.setLastModifyDate(LocalDateTime.now().toString());
			Subscription essential = subscriptionService.findById(1l);
			saasUser.setSubscription(essential);

			if (bindingResult.hasErrors()) {
				// Si des erreurs de validation sont présentes, retour à la page du formulaire

				ModelAndView mv = new ModelAndView("saas-signup-es");
				mv.addObject("css", "/resources/css/saas/signup-form.css");
				mv.addObject("saasUser", saasUser); // renvoie le user pour garder les informations saisies
				mv.addObject("errors", bindingResult.getAllErrors()); // renvoie les erreurs
				return mv;

			} else {
				
				try {

				// Création du user avec une amap, une configuration et un espace AMAP
				AMAP amap = new AMAP();
				amap.setSaasUser(saasUser);
				saasUser.setAmap(amap);

				Configuration configuration = new Configuration();

				AmapSpace amapSpace = new AmapSpace();
				amapSpace.setAmap(amap);
				amapSpace.setConfiguration(configuration);
				amapSpace.setSubscription(essential);

				amapSpaceService.save(amapSpace);
				saasUserService.save(saasUser);

				ModelAndView mv = new ModelAndView("saasuser-signup-payment");
				mv.addObject("css", "/resources/css/saas/payment.css");
				return mv;
				} catch (Exception e) {
					e.printStackTrace();
				    List<String> errors = new ArrayList<>();
				    errors.add("Une erreur s'est produite lors de l'inscription. Veuillez réessayer.");
				    ModelAndView mv = new ModelAndView("saas-signup-es");
				    mv.addObject("errorMessages", errors);
				    mv.addObject("css", "/resources/css/saas/signup-form.css");
					mv.addObject("saasUser", saasUser);
				    return mv;
				}

			}
		} else {
			return new ModelAndView("redirect:/profile");
		}
	}

	@PostMapping("/saveSignUpStandard")
	public ModelAndView saveUserSaasStandard(@Valid @ModelAttribute("saasUser") SaasUser saasUser,
			BindingResult bindingResult, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		// Vérifie si l'utilisateur n'est pas déjà authentifié
		if (authentication == null || !authentication.isAuthenticated()
				|| "anonymousUser".equals(authentication.getPrincipal())) {

			SaasUser saasUserControl = saasUserService.findByEmail(saasUser.getEmail());
			if (saasUserControl != null){
				ModelAndView mv = new ModelAndView("saas-signup-st");
				List<String> errors = new ArrayList<>();
			    errors.add("Un utilisateur avec cet email existe déjà.");
			    mv.addObject("css", "/resources/css/saas/signup-form.css");
				mv.addObject("saasUser", saasUser); 
				mv.addObject("errorMessages", errors);
				return mv;
			}
			saasUser.setSaasUserLevel(SaasUserLevel.SAAS_CUSTOM);
			saasUser.setCreateDate(LocalDateTime.now().toString());
			saasUser.setLastModifyDate(LocalDateTime.now().toString());

			Subscription standard = subscriptionService.findById(2l);
			saasUser.setSubscription(standard);

			if (bindingResult.hasErrors()) {
				// Si des erreurs de validation sont présentes, retour à la page du formulaire

				ModelAndView mv = new ModelAndView("saas-signup-st");
				mv.addObject("css", "/resources/css/saas/signup-form.css");
				mv.addObject("saasUser", saasUser); // renvoie le user pour garder les informations saisies
				mv.addObject("errors", bindingResult.getAllErrors()); // renvoie les erreurs
				return mv;

			} else {
				try {
				// Création du user avec une amap, une configuration et un espace AMAP
				AMAP amap = new AMAP();
				amap.setSaasUser(saasUser);
				saasUser.setAmap(amap);

				Configuration configuration = new Configuration();

				AmapSpace amapSpace = new AmapSpace();
				amapSpace.setAmap(amap);
				amapSpace.setConfiguration(configuration);
				amapSpace.setSubscription(standard);

				amapSpaceService.save(amapSpace);
				saasUserService.save(saasUser);

				ModelAndView mv = new ModelAndView("saasuser-signup-payment");
				mv.addObject("css", "/resources/css/saas/payment.css");
				return mv;
			} catch (Exception e) {
				e.printStackTrace();
			    List<String> errors = new ArrayList<>();
			    errors.add("Une erreur s'est produite lors de l'inscription. Veuillez réessayer.");
			    ModelAndView mv = new ModelAndView("saas-signup-st");
			    mv.addObject("errorMessages", errors);
			    mv.addObject("css", "/resources/css/saas/signup-form.css");
				mv.addObject("saasUser", saasUser);
			    return mv;
			}
			}
		} else {
			return new ModelAndView("redirect:/profile");
		}

	}

	@PostMapping("/saveSignUpPremium")
	public ModelAndView saveUserSaasPremium(@Valid @ModelAttribute("saasUser") SaasUser saasUser,
			BindingResult bindingResult, Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()
				|| "anonymousUser".equals(authentication.getPrincipal())) {
			
			SaasUser saasUserControl = saasUserService.findByEmail(saasUser.getEmail());
			if (saasUserControl != null){
				ModelAndView mv = new ModelAndView("saas-signup-pr");
				List<String> errors = new ArrayList<>();
			    errors.add("Un utilisateur avec cet email existe déjà.");
			    mv.addObject("css", "/resources/css/saas/signup-form.css");
				mv.addObject("saasUser", saasUser); 
				mv.addObject("errorMessages", errors);
				return mv;
			}
					
			saasUser.setSaasUserLevel(SaasUserLevel.SAAS_CUSTOM);
			saasUser.setCreateDate(LocalDateTime.now().toString());
			saasUser.setLastModifyDate(LocalDateTime.now().toString());
			Subscription premium = subscriptionService.findById(3l);
			saasUser.setSubscription(premium);
			if (bindingResult.hasErrors()) {
				ModelAndView mv = new ModelAndView("saas-signup-pr");
				mv.addObject("css", "/resources/css/saas/signup-form.css");
				mv.addObject("saasUser", saasUser); // renvoie le user pour garder les informations saisies
				mv.addObject("errors", bindingResult.getAllErrors()); // renvoie les erreurs
				return mv;

			} else {

				try {
				AMAP amap = new AMAP();
				amap.setSaasUser(saasUser);
				saasUser.setAmap(amap);

				Configuration configuration = new Configuration();

				AmapSpace amapSpace = new AmapSpace();
				amapSpace.setAmap(amap);
				amapSpace.setConfiguration(configuration);
				amapSpace.setSubscription(premium);

				amapSpaceService.save(amapSpace);
				saasUserService.save(saasUser);

				ModelAndView mv = new ModelAndView("saasuser-signup-payment");
				mv.addObject("css", "/resources/css/saas/payment.css");
				return mv;
			} catch (Exception e) {
				e.printStackTrace();
			    List<String> errors = new ArrayList<>();
			    errors.add("Une erreur s'est produite lors de l'inscription. Veuillez réessayer.");
			    ModelAndView mv = new ModelAndView("saas-signup-pr");
			    mv.addObject("errorMessages", errors);
			    mv.addObject("css", "/resources/css/saas/signup-form.css");
				mv.addObject("saasUser", saasUser);
			    return mv;
			}
			}

		} else {
			return new ModelAndView("redirect:/profile");

		}
	}
}
