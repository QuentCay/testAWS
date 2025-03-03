package fr.seve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.seve.entities.AMAP;
import fr.seve.entities.Subscription;
import fr.seve.service.AmapService;
import fr.seve.service.SubscriptionService;


@Controller
@RequestMapping("/saas")
public class SaasController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private AmapService amapService;
	
	@GetMapping
	public ModelAndView homeSaas() {
		ModelAndView mv = new ModelAndView("saas-home");
        mv.addObject("css", "/resources/css/saas/home.css");
        
        List<Subscription> subscriptions = subscriptionService.findAll();
		if (subscriptions.isEmpty()) {
			subscriptionService.initialize(subscriptions);	
		}
        return mv;
		}
	
	@GetMapping("/packages")
	public ModelAndView subscriptionSaas() {
		ModelAndView mv = new ModelAndView("saas-subscription");
        mv.addObject("css", "/resources/css/saas/subscription.css");
        return mv;
	}
	
	
	@GetMapping("/our-amaps")
	public ModelAndView listAmaps(Model model) {
		List<AMAP> amaps = amapService.findAll();
		model.addAttribute("amaps", amaps);
		ModelAndView mv = new ModelAndView("amap-list");
        mv.addObject("css", "/resources/css/saas/home.css");
        return mv;
	}
	
	@GetMapping("/contact")
	public ModelAndView contactSaas() {
		ModelAndView mv = new ModelAndView("saas-contact");
        mv.addObject("css", "/resources/css/saas/contact.css");
        return mv;
		}
}
