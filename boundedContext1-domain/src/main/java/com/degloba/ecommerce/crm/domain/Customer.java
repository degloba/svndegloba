package com.degloba.ecommerce.crm.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//import pl.com.bottega.ddd.annotations.domain.AggregateRoot;
import com.degloba.domain.BaseAggregateRoot;
import com.degloba.ecommerce.canonicalmodel.events.CustomerStatusChangedEvent;

/**
 * @author degloba
 *
 */
@Entity
//@AggregateRoot
public class Customer extends BaseAggregateRoot{

	public enum CustomerStatus{
		STANDARD, VIP, PLATINUM
	}
	
	@Enumerated(EnumType.STRING)
	private CustomerStatus status;
	
	
	public void changeStatus(CustomerStatus status){
		if (status.equals(this.status))
			return;
		
		this.status = status;
		
		//Sample Case: give 10% rebate for all draft orders - communication via events with different Bounded Context to achieve decoupling
		eventPublisher.publish(new CustomerStatusChangedEvent(getAggregateId(), status));
	}
}
