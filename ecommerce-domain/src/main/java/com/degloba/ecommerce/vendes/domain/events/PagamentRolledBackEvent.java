package com.degloba.ecommerce.vendes.domain.events;

import java.io.Serializable;
import java.util.Date;

import com.degloba.domain.events.DomainEvent;
import com.degloba.events.annotations.Event;
import com.degloba.events.api.IDomainEvent;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;

/**
 * @category s'ha produit un rollback d'un {@link Pagament}
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

}
