package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.jpa;


import java.io.Serializable;
import java.lang.annotation.Annotation;

import javax.persistence.*;

import com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.BaseAggregateRoot;


@Entity
public class Subcategory extends BaseAggregateRoot 
	implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
  
   	public Subcategory() {
		super();
		// TODO Auto-generated constructor stub
	}

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
    private Category category;

    
    // getters and setters
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
