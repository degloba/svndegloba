package com.degloba.persistence.rdbms.jpa;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Column;

import com.degloba.domain.annotations.ValueObject;


/**
 * Client's snapshot
 * 
 * @author degloba
 */
@ValueObject
@Embeddable
public class ClientData {
	
	@Embedded
	@AttributeOverrides({
			  @AttributeOverride(name = "aggregateId", column = @Column(name = "clientId", nullable = false))})
	private AggregateId aggregateId;
	
	private String name;

	public ClientData(){}
	
	public ClientData(AggregateId aggregateId, String name) {
		this.aggregateId = aggregateId;
		this.name = name;
	}
	
	public AggregateId getAggregateId() {
		return aggregateId;
	}
	
	public String getName() {
		return name;
	}

}

