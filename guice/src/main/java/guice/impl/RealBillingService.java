package guice.impl;

import com.degloba.billing.IBillingService;
import com.degloba.billing.ChargeResult;
import com.degloba.billing.ICreditCardProcessor;
import com.degloba.billing.PizzaOrder;
import com.degloba.billing.Receipt;
import com.degloba.logs.ITransactionLog;
import com.google.inject.Inject;
import com.paypal.api.payments.CreditCard;



public class RealBillingService implements IBillingService {  
	
	private final ICreditCardProcessor processor;  
	private final ITransactionLog transactionLog;  
	
	@Inject
	public RealBillingService(ICreditCardProcessor processor,ITransactionLog transactionLog) {    
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
