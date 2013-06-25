package guice.modules;

import com.google.inject.AbstractModule;

import com.degloba.billing.BillingService;
import com.degloba.billing.CreditCardProcessor;
import com.degloba.logs.TransactionLog;

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
		  bind(TransactionLog.class).to(DatabaseTransactionLog.class);    
		  bind(CreditCardProcessor.class).to(PaypalCreditCardProcessor.class);    
		  bind(BillingService.class).to(RealBillingService.class);  
	  }
}
