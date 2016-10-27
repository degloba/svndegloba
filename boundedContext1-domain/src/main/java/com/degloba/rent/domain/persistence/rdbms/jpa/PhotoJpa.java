package com.degloba.rent.domain.persistence.rdbms.jpa;


import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Product;

import java.io.Serializable;

import javax.persistence.*;


@Entity
public class PhotoJpa extends BaseAggregateRoot 
	implements Serializable {
  	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idGcs; 
  
	
	public PhotoJpa() {
		super();
		// TODO Auto-generated constructor stub
	}

	      
	 @ManyToOne(fetch = FetchType.LAZY)
	 private Product product;

	 	
	public String getIdGcs() {
		return idGcs;
	}


	public void setIdGcs(String idGcs) {
		this.idGcs = idGcs;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}

}