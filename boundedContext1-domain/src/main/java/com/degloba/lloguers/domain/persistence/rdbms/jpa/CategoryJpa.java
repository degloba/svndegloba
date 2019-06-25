package com.degloba.lloguers.domain.persistence.rdbms.jpa;


import javax.persistence.*;

import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;


import java.io.Serializable;
import java.util.*;

/**
 * Entitat (JPA) : Categoria
 * 
 * @author pere
 *
 */
@Entity
public class CategoryJpa extends BaseAggregateRoot implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private String description;

	public CategoryJpa() {
		super();	
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