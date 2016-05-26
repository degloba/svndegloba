package com.degloba.identityToolkit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Servlet implementation class WidgetServlet
 */
public class WidgetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WidgetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

	      StringBuilder builder = new StringBuilder();
	      String line;
	      try {
	        while ((line = request.getReader().readLine()) != null) {
	          builder.append(line);
	        }
	      } catch (IOException e) {
	        throw new RuntimeException(e);
	      }
	      String postBody = URLEncoder.encode(builder.toString(), "UTF-8");

	      try {
	        response.getWriter().print(new Scanner(new File("templates/gitkit-widget.html"), "UTF-8")
	            .useDelimiter("\\A").next()
	            .replaceAll("JAVASCRIPT_ESCAPED_POST_BODY", postBody)
	            .toString());
	        response.setStatus(HttpServletResponse.SC_OK);
	      } catch (FileNotFoundException e) {
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        response.getWriter().print(e.toString());
	      }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
