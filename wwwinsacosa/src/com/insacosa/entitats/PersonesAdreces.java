package com.insacosa.entitats;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.google.appengine.api.datastore.Key;


@Entity
public class PersonesAdreces {

	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Key key;
	
	private Persona persona;
	private Adreces adreces;

	public PersonesAdreces() {
	}

	public PersonesAdreces(Adreces adreces) {
		this.adreces = adreces;
	}

	public PersonesAdreces(Persona persona, Adreces adreces) {
		this.persona = persona;
		this.adreces = adreces;
	}

    // Accessors for the fields. JPA doesn't use these, but your application does.    
    public Key getKey() {        
    	return key;    
    	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_persona_pa")
	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Adreces getAdreces() {
		return this.adreces;
	}

	public void setAdreces(Adreces adreces) {
		this.adreces = adreces;
	}

}
