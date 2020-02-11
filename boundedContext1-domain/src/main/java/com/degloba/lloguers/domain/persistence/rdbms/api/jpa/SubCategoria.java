package com.degloba.lloguers.domain.persistence.rdbms.api.jpa;


import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.persistence.rdbms.jpa.BaseEntity;


@Entity
public class SubCategoria extends BaseAggregateRoot implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId	
	@AttributeOverrides({
		  @AttributeOverride(name = "aggregateId", column = @Column(name = "subcategoryId", nullable = false))})
	@Column(name="subcategoryId")
	protected AggregateId aggregateId;
	
	
	String description;
  
   	public SubCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}
    

   	@ManyToOne
    private Categoria category;
	
    
    // getters and setters
    
	public String getDescripcio() {
		return description;
	}

	public void setDescripcio(String description) {
		this.description = description;
	}

	public Categoria getCategory() {
		return category;
	}

	public void setCategory(Categoria category) {
		this.category = category;
	}


}
