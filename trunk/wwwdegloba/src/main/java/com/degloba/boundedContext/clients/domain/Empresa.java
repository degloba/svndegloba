package com.degloba.boundedContext.clients.domain;

// JPA
import javax.persistence.Entity;

// BaseEntity = NO aggregate


import domain.annotations.DomainEntity;
import domain.support.BaseAggregateRoot;

@Entity
@DomainEntity
public class Empresa extends BaseAggregateRoot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
