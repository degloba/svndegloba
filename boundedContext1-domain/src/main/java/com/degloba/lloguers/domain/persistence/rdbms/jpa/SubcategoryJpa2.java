package com.degloba.lloguers.domain.persistence.rdbms.jpa;


import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import com.degloba.domain.annotations.ValueObject;
import com.degloba.persistence.rdbms.jpa.BaseEntity;

import com.degloba.persistence.rdbms.jpa.BaseEntity;


@Entity
@ValueObject
public class SubcategoryJpa2 extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
/*	@EmbeddedId	
	@AttributeOverrides({
		  @AttributeOverride(name = "aggregateId", column = @Column(name = "subcategoryId", nullable = false))})
	@Column(name="subcategoryId")
	protected AggregateId aggregateId;*/
	
	
	String descripcio;
  
   	public SubcategoryJpa2() {
		super();
		// TODO Auto-generated constructor stub
	}
    
   
//  	@JoinColumn(name = "aggregateId", referencedColumnName = "aggregateId", insertable = false, updatable = false)
   	@ManyToOne
    private CategoryJpa2 category;
	
    
    // getters and setters
    
	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public CategoryJpa2 getCategory() {
		return category;
	}

	public void setCategory(CategoryJpa2 category) {
		this.category = category;
	}

	@Override
	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}


	/*public AggregateId getAggregateId() {
		return aggregateId;
	}

	public void setAggregateId(AggregateId aggregateId) {
		this.aggregateId = aggregateId;
	}*/


/*	@Override
	public Serializable getId() {
		return this.aggregateId;
	}*/
	
	
}
