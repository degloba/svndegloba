package com.degloba.organisation.ui.webui.spring.controller;


import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.degloba.security.spring.gae.security.AppRole;
import com.degloba.security.spring.gae.security.GaeUserAuthentication;
import com.degloba.security.spring.gae.users.GaeUser;
import com.degloba.security.spring.gae.users.UserRegistry;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarList;

import com.google.api.services.calendar.model.CalendarListEntry;


import com.google.appengine.api.appidentity.AppIdentityService;
import com.google.appengine.api.appidentity.AppIdentityService.GetAccessTokenResult;
import com.google.appengine.api.appidentity.AppIdentityServiceFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;

@Controller
public class HomeController {

	
		private AuthenticationDetailsSource ads = new WebAuthenticationDetailsSource();
		private AuthenticationManager authenticationManager;
		private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
	  
  
	  
    @RequestMapping(value="/oauth/userHome", method = RequestMethod.GET)
    public void getUserProfile(Model model) {
    	
  /*  	ArrayList scopes = new java.util.ArrayList();                                                                                                    
    	scopes.add("https://www.googleapis.com/auth/calendar");                                                                                       
    	AppIdentityService appIdentity = AppIdentityServiceFactory.getAppIdentityService();                                                                       
    	GetAccessTokenResult accessToken = appIdentity.getAccessToken(scopes);

    	
    	//if your application needs domain-wide access to the Google Drive API and the Google Calendar API, 
    	// enter: https://www.googleapis.com/auth/drive, https://www.googleapis.com/auth/calendar. 
    	 AppIdentityCredential credential = new AppIdentityCredential(Arrays.asList("https://www.googleapis.com/auth/calendar"));
    	 
    	 Calendar calendar = new Calendar.Builder(new UrlFetchTransport(), new JacksonFactory(), credential).
    			 setApplicationName("Casino").build();
    	 
    	 String pageToken = null;

    	 
    	 CalendarList calendarList;
		try {
			calendarList = calendar.calendarList().list().setPageToken(pageToken).execute();
			
		 	  List<CalendarListEntry> items = calendarList.getItems();

	    	  for (CalendarListEntry calendarListEntry : items) {
	    	    System.out.println(calendarListEntry.getSummary());
	    	  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Initialize Calendar service with valid OAuth credentials
		Calendar service = new Calendar.Builder(new UrlFetchTransport(), new JacksonFactory(), credential)
		    .setApplicationName("Casino").build();


		try {
			// Iterate through entries in calendar list
			String pageToken = null;
			do {
			  CalendarList calendarList;
			  
			calendarList = service.calendarList().list().setPageToken(pageToken).execute();
			
			 List<CalendarListEntry> items = calendarList.getItems();

			  for (CalendarListEntry calendarListEntry : items) {
			    System.out.println(calendarListEntry.getSummary());
			  }
			  pageToken = calendarList.getNextPageToken();
			} while (pageToken != null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
   

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String name= authentication.getName();
    	Object o = authentication.getCredentials();
    	Object googleUser =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       /* GaeUser currentUser = userRegistry.findUser(googleUser.getUserId());
        Set<AppRole> roles = EnumSet.of(AppRole.USER);

        if (UserServiceFactory.getUserService().isUserAdmin()) {
            roles.add(AppRole.ADMIN);
        }

        
        // Update the context with the full authentication
        SecurityContextHolder.getContext().setAuthentication(new GaeUserAuthentication(user, authentication.getDetails()));*/
		
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    
	    }
}
