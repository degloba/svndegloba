package com.degloba.ecommerce.vendes.domain.events;

import java.io.Serializable;
import java.util.Date;

import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.domain.event.DomainEvent;
import com.degloba.event.annotations.Event;
import com.degloba.event.domain.IDomainEvent;

/**
 * @category Rollback d'in {@link Pagament}
 * 
 * @author degloba
 *
 */
@Event
public class PagamentRolledBackEvent extends DomainEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AggregateId pagamentId;
	
	public PagamentRolledBackEvent(AggregateId pagamentId){
		this.pagamentId = pagamentId;
	}
	
	public AggregateId getPagamentId() {
		return pagamentId;
	}


	public boolean sameEventAs(Object altraEvent) {
		// TODO Auto-generated method stub
		return false;
	}

}
