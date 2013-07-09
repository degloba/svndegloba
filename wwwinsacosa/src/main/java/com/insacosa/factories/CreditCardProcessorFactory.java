package com.insacosa.factories;

import com.degloba.billing.ICreditCardProcessor;
import com.insacosa.impl.PaypalCreditCardProcessor;


public class CreditCardProcessorFactory {
	
	 private static ICreditCardProcessor instance;   
	 
	 public static void setInstance(ICreditCardProcessor creditCardProcessor) {    
		 instance = creditCardProcessor;  
		 }  
	 
	 public static ICreditCardProcessor getInstance() {    
		 if (instance == null) {      
		 return new PaypalCreditCardProcessor();    
		 }        
	 
	 return instance;  
	 }
}
