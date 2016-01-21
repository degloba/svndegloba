package com.degloba.organisation.ui.webui.spring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.degloba.security.spring.gae.users.GaeUser;

@Controller
public class HomeController {

	  
    @RequestMapping(value="/gae/userHome", method = RequestMethod.GET)
    public void getUserProfile(Model model) {
         	
    	Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		GaeUser currentUser = (GaeUser) authentication.getPrincipal();
	
		
        model.addAttribute("user", "hola");
    
	    }
}
