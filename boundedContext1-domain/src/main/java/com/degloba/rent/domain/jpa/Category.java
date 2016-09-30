package com.degloba.rent.domain.jpa;



import javax.persistence.*;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;

import java.io.Serializable;
import java.util.*;

@Entity
public class Category extends BaseAggregateRoot
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


	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Subcategory> subcategories = new ArrayList<Subcategory>();
	
	
	 // getters and setters
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Subcategory> getSubcategories() {
		return subcategories;
	}


	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}




	
	
}
