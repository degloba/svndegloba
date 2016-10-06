package com.degloba.rent.application.objectify.impl;

import javax.inject.Inject;

import com.degloba.rent.application.objectify.api.CategoryService;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.ICategoryRepository;



public class CategoryApplicationImpl implements CategoryService {

	@Inject
	private ICategoryRepository categoryRepositoryObjectify;

	
	public CategoryApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryApplicationImpl(ICategoryRepository categoryRepositoryObjectify) {
		super();
		this.categoryRepositoryObjectify = categoryRepositoryObjectify;
	}
	
	
	@Override
	public void createCategory(Category category) {
		categoryRepositoryObjectify.create(category);
	}

	public ICategoryRepository getCategoryRepositoryObjectify() {
		return categoryRepositoryObjectify;
	}

	public void setCategoryRepositoryObjectify(ICategoryRepository categoryRepositoryObjectify) {
		this.categoryRepositoryObjectify = categoryRepositoryObjectify;
	}

	
	
	
}
