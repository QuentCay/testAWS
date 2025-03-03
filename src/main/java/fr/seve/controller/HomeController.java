package fr.seve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.seve.entities.AMAP;
import fr.seve.service.AmapService;


@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private AmapService amapService;
	
	@GetMapping
	public String redirectMainPage() {
		return "redirect:/home";
	}

	@GetMapping("/home")
	public String home() {
		return "redirect:/saas";
//		List<AMAP> amaps = amapService.findAll();
//		model.addAttribute("amaps", amaps);
//		return "home";
	}
	

}
