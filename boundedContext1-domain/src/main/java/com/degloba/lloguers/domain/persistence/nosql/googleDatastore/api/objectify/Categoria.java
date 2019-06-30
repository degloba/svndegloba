package com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.io.Serializable;
import java.util.*;

/**
 * Entitat (Objectify) : Categoria 
 */
@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	Long id;
	
	String description;
	

	public Categoria() {
		super();		
		// TODO Auto-generated constructor stub
	}

	
    List<Key<SubCategoria>> subCategorias = new ArrayList<Key<SubCategoria>>();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescripcio() {
		return description;
	}


	public void setDescripcio(String description) {
		this.description = description;
	}


	public List<Key<SubCategoria>> getSubcategories() {
		return subCategorias;
	}


	public void setSubcategories(List<Key<SubCategoria>> subCategorias) {
		this.subCategorias = subCategorias;
	}
	
	
	
}
