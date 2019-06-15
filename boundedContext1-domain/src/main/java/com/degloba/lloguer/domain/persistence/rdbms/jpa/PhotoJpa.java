package com.degloba.lloguer.domain.persistence.rdbms.jpa;


import com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify.Producte;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.rdbms.jpa.BaseEntity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entitat (JPA) : Foto
 * 
 * @author pere
 *
 */
@Entity
public class PhotoJpa extends BaseAggregateRoot implements Serializable {
  	
	private static final long serialVersionUID = 1L;
	
	private String idGcs; 
  
	
	public PhotoJpa() {
		super();
		// TODO Auto-generated constructor stub
	}

	      
	 @ManyToOne(fetch = FetchType.LAZY)
	 private Producte producte;

	 	
	public String getIdGcs() {
		return idGcs;
	}


	public void setIdGcs(String idGcs) {
		this.idGcs = idGcs;
	}


	public Producte getProduct() {
		return producte;
	}


	public void setProduct(Producte producte) {
		this.producte = producte;
	}

}