package com.degloba.lloguers.domain.persistence.rdbms.api.jpa;


import javax.persistence.*;

import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;


import java.io.Serializable;
import java.util.*;

/**
 * @category Entitat (JPA)
 * 
 * @author degloba
 *
 */
@Entity
public class Categoria extends BaseAggregateRoot implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private String description;

	public Categoria() {
		super();	
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubCategoria> subcategories = new ArrayList<SubCategoria>();
	
	
	 // getters and setters
	
	public String getDescripcio() {
		return description;
	}


	public void setDescripcio(String description) {
		this.description = description;
	}


	public List<SubCategoria> getSubcategories() {
		return subcategories;
	}


	public void setSubcategories(List<SubCategoria> subcategories) {
		this.subcategories = subcategories;
	}

	
}
