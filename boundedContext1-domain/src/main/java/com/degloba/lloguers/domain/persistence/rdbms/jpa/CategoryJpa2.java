package com.degloba.lloguers.domain.persistence.rdbms.jpa;


import javax.persistence.*;

import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.rdbms.jpa.BaseEntity;


import java.io.Serializable;
import java.util.*;

@Entity
public class CategoryJpa2 extends BaseAggregateRoot implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private String description;
	
	
	/*@EmbeddedId
	@AttributeOverrides({
		  @AttributeOverride(name = "aggregateId", column = @Column(name = "categoryId", nullable = false))})
	@Column(name="categoryId")
	protected AggregateId aggregateId;*/
	
	public CategoryJpa2() {
		super();		
	}

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<SubcategoryJpa2> subcategories = new ArrayList<SubcategoryJpa2>();
	
	
	 // getters and setters
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<SubcategoryJpa2> getSubcategories() {
		return subcategories;
	}


	public void setSubcategories(List<SubcategoryJpa2> subcategories) {
		this.subcategories = subcategories;
	}


	/*public AggregateId getAggregateId() {
		return aggregateId;
	}


	public void setAggregateId(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}*/

	protected void domainError(String message) {
//		throw new DomainOperationException(getAggregateId(), message);
	}


	/*@Override
	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}*/


	/*@Override
	public Serializable getId() {		
		return this.aggregateId;
	}*/
	
}
