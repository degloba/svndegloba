package guice;

import com.google.inject.servlet.ServletModule;


/**
 * @author degloba
 * Installing the servlet module automatically gives you access to several classes from the servlet framework.
 *
 */
public class YourServletModule extends ServletModule {
	
	@Override protected void configureServlets() {    
		//serve("/remote_api").with(<AQUI VA EL SERVLET QUE VOLEM QUE GUICE INSTANCII.AQUEST SERVLET S'HA DE PODER ACCEDIR>);
		//filter("/process-user*").through(createUserIdScopingFilter());    
		}

}
