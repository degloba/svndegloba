package guice.impl;

import com.degloba.billing.BillingService;
import com.degloba.billing.ChargeResult;
import com.degloba.billing.CreditCardProcessor;
import com.degloba.billing.PizzaOrder;
import com.degloba.billing.Receipt;
import com.degloba.logs.TransactionLog;
import com.paypal.api.payments.CreditCard;



public class RealBillingService implements BillingService {  
	
	private final CreditCardProcessor processor;  
	private final TransactionLog transactionLog;  
	
	public RealBillingService(CreditCardProcessor processor,TransactionLog transactionLog) {    
		this.processor = processor;    
		this.transactionLog = transactionLog;  
		}  
	
	public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {    
		//try {      
			ChargeResult result = processor.charge(creditCard, order.getAmount());      
			//transactionLog.logChargeResult(result);      
			return  (result.wasSuccessful()          
					? Receipt.forSuccessfulCharge(order.getAmount())          
							: Receipt.forDeclinedCharge(result.getDeclineMessage()));     
	/*		} 
		catch (UnreachableException e) {      
			transactionLog.logConnectException(e);  
			
			return Receipt.forSystemFailure(e.getMessage());    
			} */ 
		}

		
		
		}
