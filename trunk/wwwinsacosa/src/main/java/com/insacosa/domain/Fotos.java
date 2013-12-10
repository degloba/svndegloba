package com.insacosa.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;

import domain.BaseAggregateRoot;
import domain.annotations.DomainAggregateRoot;


@Entity
@DomainAggregateRoot
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Fotos extends BaseAggregateRoot{

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private String id;
	
	private Key key;
	
	@ManyToOne
	private Inmobles inmobles;
	private byte[] imatge;
	private String descripcio;

	public Fotos() {
	}

	public Fotos(Key key) {
		this.key = key;
	}

	
	public Fotos(Key key, Inmobles inmobles, byte[] imatge, String descripcio) {
		this.key = key;
		this.inmobles = inmobles;
		this.imatge = imatge;
		this.descripcio = descripcio;
	}

	 // Accessors for the fields. JPA doesn't use these, but your application does.    
/*	public Key getKey() {        
		return key;    
		}
	
	public void setKey(Key key) {
		this.key = key;
	}*/
		

/*	@Column(name = "IMATGE")
	public byte[] getImatge() {
		return this.imatge;
	}

	public void setImatge(byte[] imatge) {
		this.imatge = imatge;
	}*/

	@Column(name = "DESCRIPCIO", length = 50)
	public String getDescripcio() {
		return this.descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

}
