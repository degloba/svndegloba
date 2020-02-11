package com.degloba.lloguers.domain.persistence.rdbms.api.jpa;


import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Producte;
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
public class Foto extends BaseAggregateRoot implements Serializable {
  	
	private static final long serialVersionUID = 1L;
	
	private String idGcs; 
  
	
	public Foto() {
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
