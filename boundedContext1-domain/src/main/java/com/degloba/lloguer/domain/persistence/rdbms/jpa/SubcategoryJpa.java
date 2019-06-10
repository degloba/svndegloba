package com.degloba.rent.domain.persistence.rdbms.jpa;


import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.rdbms.jpa.BaseEntity;


@Entity
public class SubcategoryJpa extends BaseAggregateRoot implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId	
	@AttributeOverrides({
		  @AttributeOverride(name = "aggregateId", column = @Column(name = "subcategoryId", nullable = false))})
	@Column(name="subcategoryId")
	protected AggregateId aggregateId;
	
	
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
