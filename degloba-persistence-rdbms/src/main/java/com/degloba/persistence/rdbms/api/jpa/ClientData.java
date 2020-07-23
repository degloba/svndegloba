package com.degloba.persistence.rdbms.api.jpa;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Column;

import com.degloba.domain.annotations.ValueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * Client's snapshot
 * 
 * @author degloba
 */
@ValueObject
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientData {
	
	@Getter
	@Embedded
	@AttributeOverrides({
			  @AttributeOverride(name = "aggregateId", column = @Column(name = "clientId", nullable = false))})
	private AggregateId aggregateId;
	
	private String name;

}

