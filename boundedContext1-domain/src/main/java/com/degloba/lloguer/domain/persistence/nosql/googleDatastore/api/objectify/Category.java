package com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.io.Serializable;
import java.util.*;

/**
 * Entitat (Objectify) : Categoria 
 */
@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	Long id;
	
	String description;
	

	public Category() {
		super();		
		// TODO Auto-generated constructor stub
	}

	
    List<Key<Subcategory>> subcategories = new ArrayList<Key<Subcategory>>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<Key<Subcategory>> getSubcategories() {
		return subcategories;
	}


	public void setSubcategories(List<Key<Subcategory>> subcategories) {
		this.subcategories = subcategories;
	}
	
	
	
}
