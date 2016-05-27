package com.degloba.rent.ui.webui.spring.controller;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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

import com.degloba.identityToolkit.GitkitClient;
import com.degloba.identityToolkit.GitkitClientException;
import com.degloba.identityToolkit.GitkitUser;
import com.degloba.usuaris.domain.IGCMTokenRegisterRepository;


@Controller
public class RentYourStuffController {  
/*	
	@Inject
	private IGCMTokenRegisterRepository GCMTokenRegisterRepository; 
*/	
		
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
		
		GitkitClient gitkitClient = GitkitClient
				.newBuilder()
				.setGoogleClientId(googleClientId)					
				.setServiceAccountEmail(serviceAccountEmail)
				.setKeyStream(keyStream)
				.setWidgetUrl("http://www.degloba.com/gitkit")
				.setCookieName("gtoken")
				.setServerApiKey("AIzaSyCnKOhcCFk24l148XUy34lwCLchldbKEYU")
				.build();
    	
    	
		GitkitUser gitkitUser = null;
            
	    // Això no funciona en GAE!!!
	    //GitkitClient gitkitClient = GitkitClient.createFromJson("gitkit-server-config.json");
	  	
	    gitkitUser = gitkitClient.validateToken(gtoken);
	        
	    String userInfo = null;
	    if (gitkitUser != null) 
	    {	
	       userInfo = "Welcome back!<br><br> Email: " + gitkitUser.getEmail() + "<br> Id: "
	              + gitkitUser.getLocalId() + "<br> Provider: " + gitkitUser.getCurrentProvider();
	         
	       	logger.info("************** LOGINAT !!!! " + userInfo);
		}
	     else 
	     {
	    	 logger.info("************** NO LOGINAT!!!");
	    	 
	    	 return "home";
	     }
	        	
	    
	    HttpSession session = request.getSession();
	    
	    if (session.getAttribute("sessionUserKey") == null)
	    {
	    	logger.info("************* LoginController : guardem user_Id a la sessio");
	    			
	    	session.setAttribute("SessionUserKey", gitkitUser.getLocalId());
	    }
	    
	    	
	    
	    // *****************************************************************************************  
    	// PERSISTIM (https://developers.google.com/identity/toolkit/web/required-endpoints#overview)
	    // user_id
	    // *****************************************************************************************
    	   	
	    // A PROD, MongoDB no funciona
    	//GCMTokenRegisterRepository.insertGCMTokenRegister(regID);
    		
	      } catch (FileNotFoundException | GitkitClientException | JSONException e) {
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

