package com.degloba.boundedContext.domain;

// JPA
import javax.persistence.Entity;

// BaseEntity = NO aggregate


import domain.annotations.DomainEntity;
import domain.support.BaseEntity;

@Entity
@DomainEntity
public class Client extends BaseEntity {

}
