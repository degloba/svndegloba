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
		
/*		@Override  
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
			
		}*/



		public void destroy() {
			// TODO Auto-generated method stub
			
		}

		public void doFilter(ServletRequest arg0, ServletResponse arg1,
				FilterChain arg2) throws IOException, ServletException {
			// TODO Auto-generated method stub
			// Make sure set() is only called if the current namespace is not already set.    
			if (NamespaceManager.get() == null) {      
				NamespaceManager.set(NamespaceManager.getGoogleAppsNamespace());    
			}  
			
		}

		public void init(FilterConfig arg0) throws ServletException {
			// TODO Auto-generated method stub
			
		}


}
