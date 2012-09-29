package com.degloba.MotorBusqueda;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



	/**
	 * This servlet is used to deal with the search request
	 * and return the search results to the client
	 */
/**
 * Title: 
 * Description: 
 * Copyright:    Copyright (c) 2009
 * Company: Summa
 * @author Pere Santasusana
 * @version 1.0
 */

	public class SearchController extends HttpServlet{

	    private static final long serialVersionUID = 1L;

	    public void doPost(HttpServletRequest request, HttpServletResponse response)
	                      throws IOException, ServletException{
	        String searchWord = request.getParameter("searchWord");
	        
			InputStream isProperties = getClass().getResourceAsStream("/gestacti.properties");
			Properties dbProps = new Properties();
				    try {
				        dbProps.load(isProperties);
				    }
				    catch (Exception e) {
				        System.err.println("Can't read the properties file. " +
				            "Make sure db.properties is in the CLASSPATH");
				        return;
				    }
				    
			String pathWeb = request.getRealPath("/");
				    
			pathWeb = dbProps.getProperty("gestacti.indexdir", pathWeb );
				    
	    	SearchManager sm = new SearchManager(pathWeb + "indexDir" , searchWord);
	        
	        List searchResult = null;
	        searchResult = sm.search();
	        RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
	        request.setAttribute("searchResult",searchResult);
	        dispatcher.forward(request, response);
	    }

	    public void doGet(HttpServletRequest request, HttpServletResponse response)
	                     throws IOException, ServletException{
	        doPost(request, response);
	    }
	}


