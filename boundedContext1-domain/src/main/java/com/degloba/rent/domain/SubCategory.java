package com.degloba.rent.domain;

import com.degloba.domain.BaseAggregateRoot;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "categories")
public class SubCategory extends BaseAggregateRoot {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
  
   	public SubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
