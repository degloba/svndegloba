package com.insacosa.modules;

import com.google.inject.AbstractModule;

import com.degloba.billing.BillingService;
import com.degloba.billing.CreditCardProcessor;
import com.degloba.logs.TransactionLog;

import com.insacosa.impl.DatabaseTransactionLog;
import com.insacosa.impl.PaypalCreditCardProcessor;
import com.insacosa.impl.RealBillingService;


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
