package com.insacosa.application.listeners;

import ddd.infrastructure.events.EventListener;
import ddd.infrastructure.events.EventListeners;
import com.insacosa.domain.Customer.CustomerStatus;
import com.insacosa.domain.events.CustomerStatusChangedEvent;

/**
 * Sample listener that communicates with other Bounded Context
 * 
 * @author Slawek
 *
 */
@EventListeners
public class CustomerStatusChangedListener{

	@EventListener(asynchronous=true)	
	public void handle(CustomerStatusChangedEvent event) {
		if (event.getStatus() == CustomerStatus.VIP){
			calculateReabteForAllDraftOrders(event.getCustomerId(), 10);
		}		
	}

	/**
	 * @param customerId
	 * @param i
	 */
	private void calculateReabteForAllDraftOrders(Long customerId, int i) {
		// TODO Auto-generated method stub
		
	}

}
