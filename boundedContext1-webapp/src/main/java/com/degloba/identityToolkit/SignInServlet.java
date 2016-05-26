package com.degloba.identityToolkit;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.util.Properties;
import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/*import com.google.identitytoolkit.GitkitClient;
import com.google.identitytoolkit.GitkitClientException;
import com.google.identitytoolkit.GitkitUser;
import com.google.identitytoolkit.JsonTokenHelper;*/

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 * Google Identity Toolkit + Google App Engine (Java) 
 * URL : https://github.com/AIMMOTH/gitapp
 */


public class SignInServlet extends HttpServlet {
		
	private GitkitClient gitkitClient; 
	private Properties properties = new Properties(); 

	private final Logger logger = LoggerFactory.getLogger(getClass()); 

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();

		try {
			//InputStream stream = context.getResourceAsStream("/WEB-INF/gitapp.properties");
			//properties.load(stream);
			
			//String googleClientId = "910109996901-8c7ojdqhg8q66pgno4vu1ta1mngvr6d2.apps.googleusercontent.com";   //properties.getProperty("googleClientId");
			String googleClientId = "wwwdegloba";   
			String serviceAccountEmail = "";  //properties.getProperty("serviceAccountEmail");
			
			logger.info("Client id:" + googleClientId + "\nService Email:" + serviceAccountEmail);

			InputStream keyStream = new FileInputStream(context.getRealPath("/WEB-INF/wwwdegloba-e308deb6a13f.p12"));
			
			logger.info("Getting p12-file " + keyStream.available() + "b");
			
			gitkitClient = GitkitClient
					.newBuilder()
					.setGoogleClientId(googleClientId)					
					.setServiceAccountEmail(serviceAccountEmail)
					.setKeyStream(keyStream)
					.setWidgetUrl("http://www.degloba.com/gitkit")
					.setCookieName("gtoken")
					.setServerApiKey("AIzaSyCnKOhcCFk24l148XUy34lwCLchldbKEYU")
					.build();
		} catch (IOException e) {				
			e.printStackTrace();
		}

		super.init(config);
	}
	
	
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
      // This check prevents the "/" handler from handling all requests by default
/*      if (!"/".equals(request.getServletPath())) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return;
      }*/

     GitkitUser gitkitUser = null;
    	
      try {
        
        // Això no funciona en GAE!!!
        //GitkitClient gitkitClient = GitkitClient.createFromJson("gitkit-server-config.json");
        
       /* gitkitClient.validateToken(token)
        JsonObject jsonToken = gitkitClient.validateTokenToJson(token);
        JsonElement j = jsonToken.get(JsonTokenHelper.ID_TOKEN_USER_ID);*/
       
        logger.debug("**************" + Base64.class.getProtectionDomain().getCodeSource().getLocation());
        
        gitkitUser = gitkitClient.validateTokenInRequest(request);
        String userInfo = null;
        if (gitkitUser != null) 
          /*userInfo = "Welcome back!<br><br> Email: " + gitkitUser.getEmail() + "<br> Id: "
              + gitkitUser.getLocalId() + "<br> Provider: " + gitkitUser.getCurrentProvider();
          */
          response.getWriter().write( 
        		  		"Welcome single user back!<br><br> Email: " + gitkitUser.getEmail() + "<br> Id: " 
        		  		+ gitkitUser.getLocalId() + "<br> Provider: " + gitkitUser.getCurrentProvider()); 
        else 
        	response.getWriter().write("Ej inloggad!");
        
       
        
        /*response.getWriter().print(new Scanner(new File("templates/index.html"), "UTF-8")
            .useDelimiter("\\A").next()
              .replaceAll("WELCOME_MESSAGE", userInfo != null ? userInfo : "You are not logged in")
            .toString());*/
        response.setStatus(HttpServletResponse.SC_OK);
      } catch (FileNotFoundException | GitkitClientException | JSONException e) {
    	  
    	  logger.debug("************** ERRORRRRRR HE ARRRIBAT!!!!!!!!!!!!!!!!!!!!!!!!!! : ");
    	  
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().print(e.toString());
      }  
    }
  }