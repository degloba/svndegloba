package com.degloba.rent.application.objectify.impl;

import javax.inject.Inject;

import com.degloba.rent.application.objectify.api.CategoryService;
import com.degloba.rent.domain.objectify.Category;
import com.degloba.rent.domain.objectify.ICategoryRepositoryObjectify;



public class CategoryApplicationImpl implements CategoryService {

	@Inject
	private ICategoryRepositoryObjectify categoryRepositoryObjectify;

	
	public CategoryApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryApplicationImpl(ICategoryRepositoryObjectify categoryRepositoryObjectify) {
		super();
		this.categoryRepositoryObjectify = categoryRepositoryObjectify;
	}
	
	
	@Override
	public void createCategory(Category category) {
		categoryRepositoryObjectify.create(category);
	}

	public ICategoryRepositoryObjectify getCategoryRepositoryObjectify() {
		return categoryRepositoryObjectify;
	}

	public void setCategoryRepositoryObjectify(ICategoryRepositoryObjectify categoryRepositoryObjectify) {
		this.categoryRepositoryObjectify = categoryRepositoryObjectify;
	}

	
	
	
}
