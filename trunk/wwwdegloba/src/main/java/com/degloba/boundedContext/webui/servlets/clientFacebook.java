package com.degloba.boundedContext.webui.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.facebookapi.FacebookXmlRestClient;


public class clientFacebook extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 
		
		HttpSession session = req.getSession();

		FacebookXmlRestClient clientFB = FacebookUserFilter.getUserClient(session);
		//FacebookXmlRestClient clientFB = (FacebookXmlRestClient)session.getAttribute("facebook.user.client");
		
		System.out.println("ApiKey : " + clientFB.getApiKey());

	}
	
	

}
