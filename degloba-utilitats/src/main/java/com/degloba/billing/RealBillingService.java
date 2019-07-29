package com.degloba.billing;

import com.degloba.billing.IBillingService;
import com.degloba.billing.ICreditCardProcessor;


// logs - degloba
import com.degloba.logs.ITransactionLog;


public class RealBillingService implements IBillingService {  
	
	private final ICreditCardProcessor processor;  
	private final ITransactionLog transactionLog;  
	
	public RealBillingService(ICreditCardProcessor processor,ITransactionLog transactionLog) {    
		this.processor = processor;    
		this.transactionLog = transactionLog;  
		}  
	
/*	public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {    
		//try {      
			ChargeResult result = processor.charge(creditCard, order.getQuantitat());      
			//transactionLog.logChargeResult(result);      
			return  (result.wasSuccessful()          
					? Receipt.forSuccessfulCharge(order.getQuantitat())          
							: Receipt.forDeclinedCharge(result.getDeclineMessage()));     
			} 
		catch (UnreachableException e) {      
			transactionLog.logConnectException(e);  
			
			return Receipt.forSystemFailure(e.getMessage());    
			} 
		}*/

		
		
		}
