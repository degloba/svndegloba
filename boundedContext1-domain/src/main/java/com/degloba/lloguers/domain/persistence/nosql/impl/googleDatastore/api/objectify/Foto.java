package com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import java.io.Serializable;

/**
 * Entitat (Objectify) : Foto
 */
@Entity
public class Foto implements Serializable {
  	
	private static final long serialVersionUID = 1L;

	@Id
	Long id;
	
	String idGcs; 
  
	
	public Foto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Parent Key<Producte> producte;

	 	
	public String getIdGcs() {
		return idGcs;
	}


	public void setIdGcs(String idGcs) {
		this.idGcs = idGcs;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Key<Producte> getProduct() {
		return producte;
	}


	public void setProduct(Key<Producte> producte) {
		this.producte = producte;
	}


	
}
