package com.degloba.rent.application.jpa.impl;

import javax.inject.Inject;

import com.degloba.rent.application.jpa.api.CategoryService;
import com.degloba.rent.domain.persistence.rdbms.jpa.Category;
import com.degloba.rent.domain.persistence.rdbms.jpa.ICategoryRepository;



public class CategoryApplicationImpl implements CategoryService {

	@Inject
	private ICategoryRepository categoryRepositoryJpa;

	
	public CategoryApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CategoryApplicationImpl(ICategoryRepository categoryRepositoryJpa) {
		super();
		this.categoryRepositoryJpa = categoryRepositoryJpa;
	}
	
	
	@Override
	public void createCategory(Category category) {
		// TODO Auto-generated method stub
		categoryRepositoryJpa.save(category);
	}

	public ICategoryRepository getCategoryRepositoryJpa() {
		return categoryRepositoryJpa;
	}

	public void setCategoryRepositoryJpa(ICategoryRepository categoryRepositoryJpa) {
		this.categoryRepositoryJpa = categoryRepositoryJpa;
	}
	
	
	
	

}
