package com.degloba.casino.clients;

// JPA
import javax.persistence.Entity;

// BaseEntity = NO aggregate

import com.degloba.annotations.DomainEntity;
import com.degloba.domain.BaseAggregateRoot;

@Entity
@DomainEntity
public class Empresa extends BaseAggregateRoot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
