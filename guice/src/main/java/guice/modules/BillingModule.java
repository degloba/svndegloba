package guice.modules;

import Application.ciutats.Ciutats_app;
import Application.ciutats.ICiutats;
import Application.inmobles.IInmobles;
import Application.inmobles.Inmobles_app;
import Application.solicituds.ISolicituds;
import Application.solicituds.Solicituds_app;
import Application.usuaris.IUsuaris;
import Application.usuaris.Usuaris_app;

import com.google.inject.AbstractModule;

import com.degloba.billing.IBillingService;
import com.degloba.billing.ICreditCardProcessor;
import com.degloba.logs.ITransactionLog;

import entitats.Solicituds;

import guice.impl.DatabaseTransactionLog;
import guice.impl.PaypalCreditCardProcessor;
import guice.impl.RealBillingService;


/**
 * @author degloba
 * Guice uses bindings to map types to their implementations
 */
public class BillingModule extends AbstractModule  {
	
	  @Override   
	  protected void configure() {    
		  bind(ITransactionLog.class).to(DatabaseTransactionLog.class);    
		  bind(ICreditCardProcessor.class).to(PaypalCreditCardProcessor.class);    
		  bind(IBillingService.class).to(RealBillingService.class);
		  
		  bind(ISolicituds.class).to(Solicituds_app.class);  
		  bind(IUsuaris.class).to(Usuaris_app.class);  
		  bind(IInmobles.class).to(Inmobles_app.class);  
		  bind(ICiutats.class).to(Ciutats_app.class);  
	  }
}
