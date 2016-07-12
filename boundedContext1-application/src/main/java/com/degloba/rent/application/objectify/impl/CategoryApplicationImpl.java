package com.degloba.rent.application.objectify.impl;

import javax.inject.Inject;

import com.degloba.rent.application.jpa.api.CategoryService;
import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.ICategoryRepository;


import com.googlecode.objectify.ObjectifyService;


public class CategoryApplicationImpl implements CategoryService {

	@Inject
	private ICategoryRepository categoryRepository;

	
	public CategoryApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryApplicationImpl(ICategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	
	@Override
	public void createCategory(Category category) {
	
		// Use Objectify to save the greeting and now() is used to make the call synchronously as we
		// will immediately get a new page using redirect and we want the data to be present.
	    ObjectifyService.ofy().save().entity(category).now();

	}

}
