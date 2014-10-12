package com.degloba.boundedContext.domain;

// JPA
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.spi.StateManager;
import javax.persistence.Entity;

// BaseEntity = NO aggregate





import domain.annotations.DomainEntity;
import domain.support.BaseAggregateRoot;

@Entity
@DomainEntity
public class Client extends BaseAggregateRoot {

	
}
