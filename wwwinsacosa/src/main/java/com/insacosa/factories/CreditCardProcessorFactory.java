package com.insacosa.factories;

import com.degloba.billing.CreditCardProcessor;
import com.insacosa.impl.SquareCreditCardProcessor;


public class CreditCardProcessorFactory {
	
	 private static CreditCardProcessor instance;   
	 
	 public static void setInstance(CreditCardProcessor creditCardProcessor) {    
		 instance = creditCardProcessor;  
		 }  
	 
	 public static CreditCardProcessor getInstance() {    
		 if (instance == null) {      
		 return new SquareCreditCardProcessor();    
		 }        
	 
	 return instance;  
	 }
}
