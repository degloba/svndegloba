package com.degloba.queues;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.appengine.api.NamespaceManager;


	// Filter to set the Google Apps domain as the namespace.
	public class NamespaceFilter implements javax.servlet.Filter { 
		
		@Override  
		public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)      
				throws IOException, ServletException {    
			// Make sure set() is only called if the current namespace is not already set.    
			if (NamespaceManager.get() == null) {      
				NamespaceManager.set(NamespaceManager.getGoogleAppsNamespace());    
				}  
			}

		@Override
		public void destroy() {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
			
		}


}
