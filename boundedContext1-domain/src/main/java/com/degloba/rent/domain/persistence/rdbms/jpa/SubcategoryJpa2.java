package com.degloba.rent.domain.persistence.rdbms.jpa;


import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;


@Entity
public class SubcategoryJpa2 extends BaseAggregateRoot implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId	
	@AttributeOverrides({
		  @AttributeOverride(name = "aggregateId", column = @Column(name = "subcategoryId", nullable = false))})
	@Column(name="subcategoryId")
	protected AggregateId aggregateId;
	
	
	String description;
  
   	public SubcategoryJpa2() {
		super();
		// TODO Auto-generated constructor stub
	}
    

   	@ManyToOne
    private CategoryJpa2 category;
	
    
    // getters and setters
    
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryJpa2 getCategory() {
		return category;
	}

	public void setCategory(CategoryJpa2 category) {
		this.category = category;
	}

	public AggregateId getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}


	@Override
	public Serializable getId() {
		return this.aggregateId;
	}
	
	
}
