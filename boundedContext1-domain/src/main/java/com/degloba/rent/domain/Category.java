package com.degloba.rent.domain;

import com.degloba.domain.BaseAggregateRoot;
import com.degloba.domain.BaseEntity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
public class Category extends BaseAggregateRoot
	implements Serializable
	 {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;

  
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Subcategory> subcategories = new HashSet<Subcategory>();
	
	
	 // getters and setters
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<Subcategory> getSubcategories() {
		return subcategories;
	}


	public void setSubcategories(Set<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	
	
}
