package com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify;


import java.io.Serializable;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;


/**
 * Entitat (Objectify) : Subcategoria
 * 
 * @author pere
 *
 */
@Entity
public class SubCategoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	Long id;
	
	String description;
  
   	public SubCategoria() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    @Parent Key<Categoria> categoria;

    
    // getters and setters
    
	public String getDescripcio() {
		return description;
	}

	public void setDescripcio(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Key<Categoria> getCategory() {
		return categoria;
	}

	public void setCategory(Key<Categoria> categoria) {
		this.categoria = categoria;
	}

	

	
	
}
