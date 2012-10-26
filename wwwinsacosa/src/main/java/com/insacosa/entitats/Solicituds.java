package com.insacosa.entitats;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;


@Entity
public class Solicituds {
	
	@Id    
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Key key;
	
	private Inmobles inmobles;
	private Usuaris usuaris;

	
	 // Accessors for the fields. JPA doesn't use these, but your application does.    
	public Key getKey() {        
		return key;    
		}
	
	public Solicituds() {
	}

	public Solicituds(Key key, Inmobles inmobles, Usuaris usuaris) {
		this.key = key;
		this.inmobles = inmobles;
		this.usuaris = usuaris;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDINMOBLE")
	public Inmobles getInmobles() {
		return this.inmobles;
	}

	public void setInmobles(Inmobles inmobles) {
		this.inmobles = inmobles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPRADOR")
	public Usuaris getUsuaris() {
		return this.usuaris;
	}

	public void setUsuaris(Usuaris usuaris) {
		this.usuaris = usuaris;
	}

}
