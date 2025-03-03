package fr.seve.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import fr.seve.utils.AmapUtils;


@Controller
public class AmapAdminDashboardController {
	
	@Secured("ROLE_AMAP_ADMIN")
    @GetMapping("/{slug}/admin")
    public String handleAmapSlug(@PathVariable String slug, Model model, HttpServletRequest request) {
    	   	
    	model.addAttribute("amap", AmapUtils.getAmapFromRequest(request));
    	return "amap-admin-home";
    }
 
}
