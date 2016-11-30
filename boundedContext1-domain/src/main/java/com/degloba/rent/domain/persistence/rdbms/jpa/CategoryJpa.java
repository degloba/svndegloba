package com.degloba.rent.domain.persistence.rdbms.jpa;


import javax.persistence.*;

import com.degloba.domain.persistence.rdbms.jpa.AbstractEntity;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;

import java.io.Serializable;
import java.util.*;

@Entity
public class CategoryJpa extends BaseAggregateRoot implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private String description;
	
	
	@EmbeddedId
	@AttributeOverrides({
		  @AttributeOverride(name = "aggregateId", column = @Column(name = "categoryId", nullable = false))})
	@Column(name="categoryId")
	protected AggregateId aggregateId;

	public CategoryJpa() {
		super();
		
		this.setAggregateId(AggregateId.generate());
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


	public AggregateId getAggregateId() {
		return aggregateId;
	}


	public void setAggregateId(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}

	protected void domainError(String message) {
		throw new DomainOperationException(getAggregateId(), message);
	}


	@Override
	public Serializable getId() {		
		return this.aggregateId;
	}
	
}
