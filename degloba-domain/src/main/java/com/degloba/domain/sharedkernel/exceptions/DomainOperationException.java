package com.degloba.domain.sharedkernel.exceptions;

import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;

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

