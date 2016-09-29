package com.degloba.rent.domain.jpa;


import com.degloba.domain.jpa.BaseAggregateRoot;
import com.google.appengine.api.datastore.Key;

import java.io.Serializable;

import javax.persistence.*;


@Entity
public class Photo extends BaseAggregateRoot 
	implements Serializable {
  	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idGcs; 
  
	
	public Photo() {
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
