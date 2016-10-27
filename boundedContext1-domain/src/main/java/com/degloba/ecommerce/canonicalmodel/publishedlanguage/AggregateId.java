package com.degloba.ecommerce.canonicalmodel.publishedlanguage;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;


/*
 * JPA Implementation Patterns: Using UUIDs As Primary Keys
 */

@Embeddable
public class AggregateId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String aggregateId;

	public AggregateId(String aggregateId) {
		Validate.notNull(aggregateId);
		this.aggregateId = aggregateId;
	}

	protected AggregateId() {
	}
	
	public static AggregateId generate(){
		return new AggregateId(UUID.randomUUID().toString());
	}

	public String getId() {
		return aggregateId;
	}

	@Override
	public int hashCode() {
		return aggregateId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AggregateId other = (AggregateId) obj;
		if (aggregateId == null) {
			if (other.aggregateId != null)
				return false;
		} else if (!aggregateId.equals(other.aggregateId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return aggregateId;
	}
}
