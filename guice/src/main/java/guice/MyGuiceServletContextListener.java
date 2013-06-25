package guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import guice.modules.BillingModule;


/**
 * @author degloba
 * 
 * Guice.createInjector() takes your Modules, and returns a new Injector      
 * instance. Most applications will call this method exactly once.
 */
public class MyGuiceServletContextListener extends GuiceServletContextListener {  
	
	@Override protected Injector getInjector() {    
	/*return Guice.createInjector(        
			new YourServletModule());  */
	return Guice.createInjector(              
			new BillingModule()); 	
	}

}
