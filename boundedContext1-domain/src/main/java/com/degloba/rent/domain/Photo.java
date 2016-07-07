package com.degloba.rent.domain;

import com.degloba.domain.BaseAggregateRoot;
import com.google.appengine.api.datastore.Key;

import java.io.Serializable;

import javax.persistence.*;


@Entity
public class Photo extends BaseAggregateRoot 
	implements Serializable{
  	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idGcs; 
		
	
    private Category category;
    
    @Embedded
    private Owner owner;
  

    public Photo(Category category) {
        this.category = category;
    }
   
 
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}


	 @ManyToOne(fetch = FetchType.LAZY)
	 private Product product;

	 
	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}


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
