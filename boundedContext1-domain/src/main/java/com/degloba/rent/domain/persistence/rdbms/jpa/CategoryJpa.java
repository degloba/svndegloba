package com.degloba.rent.domain.persistence.rdbms.jpa;


import javax.persistence.*;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;

import java.io.Serializable;
import java.util.*;

@Entity
public class CategoryJpa extends BaseAggregateRoot
	implements Serializable
	 {
	

	private static final long serialVersionUID = 1L;
	private String description;
	private String nou;

  
	public String getNou() {
		return nou;
	}


	public void setNou(String nou) {
		this.nou = nou;
	}


	public CategoryJpa() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubcategoryJpa> subcategories = new ArrayList<SubcategoryJpa>();
	
	
	 // getters and setters
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<SubcategoryJpa> getSubcategories() {
		return subcategories;
	}


	public void setSubcategories(List<SubcategoryJpa> subcategories) {
		this.subcategories = subcategories;
	}


	
	
	
}
