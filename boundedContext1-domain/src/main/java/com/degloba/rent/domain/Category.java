package com.degloba.rent.domain;

import com.degloba.domain.BaseAggregateRoot;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "categories")
public class Category extends BaseAggregateRoot {

	
	private String description;

  
	public Category() {
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
