package com.degloba.rent.domain.persistence.rdbms.jpa;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;

import java.io.Serializable;

import javax.persistence.*;


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

	
	
}
