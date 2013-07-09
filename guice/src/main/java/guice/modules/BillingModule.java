package guice.modules;

import com.google.inject.AbstractModule;

import com.degloba.billing.IBillingService;
import com.degloba.billing.ICreditCardProcessor;
import com.degloba.logs.ITransactionLog;

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
	  }
}
