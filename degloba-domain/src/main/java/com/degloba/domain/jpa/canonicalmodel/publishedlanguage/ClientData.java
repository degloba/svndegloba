package com.degloba.domain.jpa.canonicalmodel.publishedlanguage;


import javax.persistence.Embeddable;

import com.degloba.domain.annotations.ValueObject;
import com.google.appengine.api.datastore.Key;

/**
 * Client's snapshot
 * 
 * @author degloba
 */
@ValueObject
@Embeddable
public class ClientData {
	
	//@Embedded
	/*@AttributeOverrides({
			  @AttributeOverride(name = "aggregateId", column = @Column(name = "clientId", nullable = false))})
*/	private Key aggregateId;
	
	private String name;

	public ClientData(){}
	
	public ClientData(Key aggregateId, String name) {
		this.aggregateId = aggregateId;
		this.name = name;
	}
	
	public Key getAggregateId() {
		return aggregateId;
	}
	
	public String getName() {
		return name;
	}

}

