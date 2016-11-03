package com.degloba.rent.domain.persistence.rdbms.jpa;


import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;


@Entity
public class SubcategoryJpa extends BaseAggregateRoot
  	implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String description;
  
   	public SubcategoryJpa() {
		super();
		// TODO Auto-generated constructor stub
	}
    
   	@ManyToOne
    private CategoryJpa category;
	
    
    // getters and setters
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryJpa getCategory() {
		return category;
	}

	public void setCategory(CategoryJpa category) {
		this.category = category;
	}



	
	
}
