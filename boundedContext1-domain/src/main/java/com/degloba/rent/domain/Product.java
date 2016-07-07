package com.degloba.rent.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.degloba.domain.BaseAggregateRoot;

@Entity
public class Product extends BaseAggregateRoot {
	
	private String description;
	private Long price;

	  
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Photo> photos = new ArrayList<Photo>();


	//
	
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
	

}
