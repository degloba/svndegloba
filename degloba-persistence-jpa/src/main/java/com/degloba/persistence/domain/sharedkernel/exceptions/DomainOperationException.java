package com.degloba.persistence.domain.sharedkernel.exceptions;

import com.degloba.persistence.domain.AggregateId;

/**
 * Exception : DomainOperationException
 * 
 * @author pere
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

