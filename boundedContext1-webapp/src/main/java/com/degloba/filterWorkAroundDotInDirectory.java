package com.degloba;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * WorkAround : en Java no s'admet carpetes web amb "."
 * 
 * Filter que mapeja les peticions /.well-known amb /well-known
 * Mirar : https://cultiv.nl/blog/lets-encrypt-on-windows/
 * Crear certificats SSL amb LetsEncrypt (gratis)
 * Solució VisualStudio letsencrypt-win
 * 
 */
public class filterWorkAroundDotInDirectory implements Filter {

    /**
     * Default constructor. 
     */
    public filterWorkAroundDotInDirectory() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        
		if (requestURI.startsWith("/.well-known")) {
            //String toReplace = requestURI.substring(requestURI.indexOf("/.well-known"), requestURI.lastIndexOf("/well-known") + 1);
            String newURI = requestURI.replace("/.well-known", "/well-known");
            req.getRequestDispatcher(newURI).forward(req, response);
        } else {
            chain.doFilter(req, response);
        }	
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
