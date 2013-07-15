package com.insacosa.modules;

import com.google.inject.AbstractModule;
import com.insacosa.Domini.IInsacosaClasseService;
import com.insacosa.Domini.InsacosaClasseService;


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
