package com.insacosa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

import ddd.domain.BaseAggregateRoot;
import ddd.domain.annotations.DomainAggregateRoot;

@Entity
@DomainAggregateRoot
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
