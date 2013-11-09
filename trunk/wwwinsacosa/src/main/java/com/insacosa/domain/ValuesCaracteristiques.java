package com.insacosa.domain;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


import ddd.domain.BaseAggregateRoot;
import ddd.domain.annotations.DomainAggregateRoot;


@Entity
@DomainAggregateRoot
public class ValuesCaracteristiques extends BaseAggregateRoot{

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private String id;
	
	private String valueCaracteristicaKey;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Inmobles inmobles;
	
	@Transient
	private Caracteristiques caracteristiques;
	

	 // Unowned relationship
    private String caracteristicaKey;

	
	private String value;

	public ValuesCaracteristiques() {
	}

	public ValuesCaracteristiques(String valueCaracteristicaKey,Caracteristiques caracteristiques, Inmobles inmobles) {
		this.valueCaracteristicaKey = valueCaracteristicaKey;
		this.caracteristiques = caracteristiques;
		this.inmobles = inmobles;
	}

	public ValuesCaracteristiques(String valueCaracteristicaKey,Caracteristiques caracteristiques, Inmobles inmobles, String value) {
		this.valueCaracteristicaKey = valueCaracteristicaKey;
		this.caracteristiques = caracteristiques;
		this.inmobles = inmobles;
		this.value = value;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public String getValueCaracteristicaKey() {        
    	return valueCaracteristicaKey;    
    	}

	
	public Caracteristiques getCaracteristiques() {
		return this.caracteristiques;
	}

	public void setCaracteristiques(Caracteristiques caracteristiques) {
		this.caracteristiques = caracteristiques;
	}

	public Inmobles getInmobles() {
		return this.inmobles;
	}

	public void setInmobles(Inmobles inmobles) {
		this.inmobles = inmobles;
	}

	@Column(name = "VALUE")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCaracteristicaKey() {
		return caracteristicaKey;
	}

	public void setCaracteristicaKey(String caracteristicaKey) {
		this.caracteristicaKey = caracteristicaKey;
	}

	public void setValueCaracteristicaKey(String valueCaracteristicaKey) {
		this.valueCaracteristicaKey = valueCaracteristicaKey;
	}
	
	

}
