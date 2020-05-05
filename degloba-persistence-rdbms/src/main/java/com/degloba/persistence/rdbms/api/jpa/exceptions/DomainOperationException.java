package com.degloba.persistence.rdbms.api.jpa.exceptions;

import com.degloba.persistence.rdbms.api.jpa.AggregateId;

/**
 * @category excepció quan s'ha fet una operació de domini 
 * 
 * @author degloba
 *
 */
@SuppressWarnings("serial")
public class DomainOperationException extends RuntimeException{

	private AggregateId aggregateId;

	public DomainOperationException(AggregateId aggregateId, String message){
		super(message);
		this.aggregateId = aggregateId;
	}
	
	public AggregateId getAggregateId() {
		return aggregateId;
	}
}

