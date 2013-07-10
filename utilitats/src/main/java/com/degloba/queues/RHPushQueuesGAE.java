package com.degloba.queues;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

	
	
/**
 * @author degloba
 * https://developers.google.com/appengine/docs/java/multitenancy/multitenancy (In the Task Queue)
 */
public class RHPushQueuesGAE extends HttpServlet {
	
	// Handler for URL get requests.  
	protected void doGet(HttpServletRequest req,                        
				HttpServletResponse resp)       
						throws IOException {
		
	// Increment the count for the current namespace asynchronously.    
	QueueFactory.getDefaultQueue().add(TaskOptions.Builder.withUrl("/worker").param("countName", "SomeRequest")); 
	
	// Increment the global count and set the    
	// namespace locally.  The namespace is    
	// transferred to the invoked request and     
	// executed asynchronously.    String namespace = NamespaceManager.get();    
	try {      
		NamespaceManager.set("-global-");      
		QueueFactory.getDefaultQueue().add(          
				TaskOptions.Builder.withUrl("/_ah/update_count")                             
						.param("countName", "SomeRequest"));    } 
	finally {      
		//NamespaceManager.set(namespace);    
	}    
	resp.setContentType("text/plain");    
	resp.getWriter().println("Counts are being updated.");  }

}
