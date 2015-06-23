package com.degloba.domain.sharedkernel.exceptions;

import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;
import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
public class DomainOperationException extends RuntimeException{

	private Key aggregateId;

	public DomainOperationException(Key aggregateId, String message){
		super(message);
		this.aggregateId = aggregateId;
	}
	
	public Key getAggregateId() {
		return aggregateId;
	}
}

