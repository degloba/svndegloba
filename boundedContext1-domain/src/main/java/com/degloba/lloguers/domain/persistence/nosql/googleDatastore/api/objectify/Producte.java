package com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify;

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
public class Producte implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	Long id;
	
	String description;
	Double price;
	Subcategory subcategory;
	
	@Parent Key<Propietari> propietari;
		
	///////////////////@Parent Key<Location> location;

	  
	public Producte() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	List<Key<Foto>> fotos = new ArrayList<Key<Foto>>();


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

	public List<Key<Foto>> getPhotos() {
		return fotos;
	}

	public void setPhotos(List<Key<Foto>> fotos) {
		this.fotos = fotos;
	}

	public void setOwner(Key<Propietari> propietari) {
		this.propietari = propietari;
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

	public Key<Propietari> getOwner() {
		return propietari;
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