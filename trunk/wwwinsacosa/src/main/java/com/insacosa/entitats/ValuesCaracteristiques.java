package com.insacosa.entitats;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;


@Entity
public class ValuesCaracteristiques {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Key key;
	
	private Caracteristiques caracteristiques;
	private Inmobles inmobles;
	private String value;

	public ValuesCaracteristiques() {
	}

	public ValuesCaracteristiques(Key key,Caracteristiques caracteristiques, Inmobles inmobles) {
		this.key = key;
		this.caracteristiques = caracteristiques;
		this.inmobles = inmobles;
	}

	public ValuesCaracteristiques(Key key,Caracteristiques caracteristiques, Inmobles inmobles, String value) {
		this.key = key;
		this.caracteristiques = caracteristiques;
		this.inmobles = inmobles;
		this.value = value;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public Key getKey() {        
    	return key;    
    	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCARACTERISTICA", nullable = false, insertable = false, updatable = false)
	public Caracteristiques getCaracteristiques() {
		return this.caracteristiques;
	}

	public void setCaracteristiques(Caracteristiques caracteristiques) {
		this.caracteristiques = caracteristiques;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDINMOBLE", nullable = false, insertable = false, updatable = false)
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

}
