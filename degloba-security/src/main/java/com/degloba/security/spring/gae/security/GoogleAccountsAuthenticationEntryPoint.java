package com.degloba.security.spring.gae.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GoogleAccountsAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger log = Logger.getLogger(GoogleAccountsAuthenticationEntryPoint.class.getName());
	
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		
		response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
		
	}
}
