package com.degloba.rent.domain.objectify;

import com.degloba.domain.BaseAggregateRoot;
import com.degloba.domain.BaseEntity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.io.Serializable;
import java.util.*;

@Entity
public class Category implements Serializable
	 {


	private static final long serialVersionUID = 1L;
	
	@Id
	Long id;
	
	String description;
	

	public Category() {
		super();		
		// TODO Auto-generated constructor stub
	}

	
    List<Key<Subcategory>> subcategories = new ArrayList<Key<Subcategory>>();
	
	
	
}
