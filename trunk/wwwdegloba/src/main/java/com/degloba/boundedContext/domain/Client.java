package com.degloba.boundedContext.domain;

// JPA
import javax.persistence.Entity;

// BaseEntity = NO aggregate
import domain.BaseEntity;
import domain.annotations.DomainEntity;

@Entity
@DomainEntity
public class Client extends BaseEntity {

}
