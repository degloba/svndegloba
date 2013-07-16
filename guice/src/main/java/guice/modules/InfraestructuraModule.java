package guice.modules;

import com.google.inject.AbstractModule;

import domini.IInsacosaClasseService;
import domini.InsacosaClasseService;


/**
 * @author degloba
 * Guice uses bindings to map types to their implementations
 */
public class InfraestructuraModule extends AbstractModule  {
	
	  @Override   
	  protected void configure() {    
		  bind(IInsacosaClasseService.class).to(InsacosaClasseService.class);     
	  }
}
