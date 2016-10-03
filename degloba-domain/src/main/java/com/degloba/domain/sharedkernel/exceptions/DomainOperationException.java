package com.degloba.domain.sharedkernel.exceptions;


@SuppressWarnings("serial")
public class DomainOperationException extends RuntimeException{

	private long aggregateId;

	public DomainOperationException(long aggregateId, String message){
		super(message);
		this.aggregateId = aggregateId;
	}
	
	public long getAggregateId() {
		return aggregateId;
	}
}

