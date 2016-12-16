package com.degloba.rent.ui.webui.spring.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;

// Spring Web
import org.springframework.web.bind.annotation.RequestMapping;


//import com.degloba.usuaris.domain.IGCMTokenRegisterRepository;
//import com.degloba.usuaris.infrastructure.jpa.repositories.GCMTokenRegisterRepository;
import com.google.appengine.api.utils.SystemProperty;


@Controller
public class RentYourStuffController {  
	
/*	@Inject
	private IGCMTokenRegisterRepository GCMTokenRegisterRepository; */
	
		
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    ServletContext context; 
	
    @RequestMapping(value = "rentYourStuff")
    public String registerTokenGCMAndroid(@CookieValue(value = "gtoken", required=false) String gtoken,
    		HttpServletRequest request,
            HttpServletResponse response) {
    	
    	    	
    if (gtoken != "null")
    {
    	
    	String googleClientId = "wwwdegloba";   
		String serviceAccountEmail = "";  //properties.getProperty("serviceAccountEmail");
	
		try {
			
		InputStream keyStream = new FileInputStream(context.getRealPath("/WEB-INF/wwwdegloba-e308deb6a13f.p12"));
		
            
	    
	    // *****************************************************************************************  
    	// PERSISTIM (https://developers.google.com/identity/toolkit/web/required-endpoints#overview)
	    // user_id
	    // *****************************************************************************************
    	   	
	    // A PROD, MongoDB no funciona
	    if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Development) {
	    	// do something that's development-only
	    	logger.info("************* Environment DEV : guardem user_Id a la sessio");
	    	
	    	/////GCMTokenRegisterRepository.insertGCMTokenRegister(gitkitUser.getLocalId().toString());
	    }
	    else {
	    	// do something that's production-only
	    	logger.info("************* Environment DEV : guardem user_Id a la sessio");
	    		
	    }
    	
    		
	      } catch (FileNotFoundException |  JSONException e) {
	        e.printStackTrace();	        
	      }
		
		return "productFlow";  	      
    }
    
    else
    {
    	logger.info("************** NO LOGINAT!!!");
   	 
   	 	return "home";
    }
   }
    
}

