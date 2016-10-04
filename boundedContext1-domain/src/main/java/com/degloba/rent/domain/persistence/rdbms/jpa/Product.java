package com.degloba.rent.domain.persistence.rdbms.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;

@Entity
public class Product extends BaseAggregateRoot 
	implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private Long price;
	private Subcategory subcategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Owner owner;
	
	private Location location;

	  
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<Photo>();


	// getters - setters
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	

}
