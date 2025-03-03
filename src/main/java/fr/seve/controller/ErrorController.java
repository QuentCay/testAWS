package fr.seve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error/403")
    public String error403() {
    	
        return "error403"; 
    }
}

