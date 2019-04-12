package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify;


import java.io.Serializable;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;


/**
 * Entitat (Objectify) : Subcategoria
 * 
 * @author pere
 *
 */
@Entity
public class Subcategory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	Long id;
	
	String description;
  
   	public Subcategory() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    @Parent Key<Category> category;

    
    // getters and setters
    
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

	public Key<Category> getCategory() {
		return category;
	}

	public void setCategory(Key<Category> category) {
		this.category = category;
	}

	

	
	
}
