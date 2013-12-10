package com.insacosa.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


import com.google.appengine.api.datastore.Key;

import domain.BaseAggregateRoot;
import domain.annotations.DomainAggregateRoot;

@Entity
@DomainAggregateRoot
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Caractinmobles extends BaseAggregateRoot{

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)    
	private Key key;

	public Caractinmobles() {
	}

	 // Accessors for the fields. JPA doesn't use these, but your application does.    
	public Key getKey() {        
		return key;    
		}


}
