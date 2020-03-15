package com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.degloba.domain.annotations.AggregateRoot;
import com.degloba.ecommerce.crm.domain.events.EstatClientCanviatEvent;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;

/**
 * @category Entitat de persistencia que defineix un client
 * 
 * @author degloba
 *
 */
@Entity
@AggregateRoot
public class Client extends BaseAggregateRoot{

	private static final long serialVersionUID = 1L;

	public enum EstatClient{
		STANDARD, VIP, PLATINUM
	}
	
	@Enumerated(EnumType.STRING)
	private EstatClient status;
	
	
	public void canviaEstatClient(EstatClient status){
		if (status.equals(this.status))
			return;
		
		this.status = status;
		
		//Sample Case: give 10% rebate for all draft orders - communication via events with different Bounded Context to achieve decoupling
		////////eventPublisher.publish(new CustomerStatusChangedEvent(getAggregateId(), status));
	}

}
