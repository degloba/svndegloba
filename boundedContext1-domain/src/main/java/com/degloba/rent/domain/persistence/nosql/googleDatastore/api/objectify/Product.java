package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

/**
 * Entitat (Objectify) : Producte
 */
@Entity
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	Long id;
	
	String description;
	Double price;
	Subcategory subcategory;
	
	@Parent Key<Owner> owner;
		
	///////////////////@Parent Key<Location> location;

	  
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	List<Key<Photo>> photos = new ArrayList<Key<Photo>>();


	// getters - setters
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Key<Photo>> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Key<Photo>> photos) {
		this.photos = photos;
	}

	public void setOwner(Key<Owner> owner) {
		this.owner = owner;
	}

	/*public void setLocation(Key<Location> location) {
		this.location = location;
	}*/

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Key<Owner> getOwner() {
		return owner;
	}

	/*public Key<Location> getLocation() {
		return location;
	}*/

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}
	
	

}
