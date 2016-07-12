package com.degloba.rent.facade.impl;

import javax.inject.Inject;

import com.degloba.rent.application.jpa.api.CategoryService;
import com.degloba.rent.domain.jpa.Category;

import com.degloba.rent.facade.CategoryFacade;


public class CategoryFacadeImpl implements CategoryFacade {

    @Inject
    protected CategoryService categoryApplication;

    public CategoryFacadeImpl(CategoryService application) {
        this.categoryApplication = application;
    }
   
	public CategoryFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createCategory(Category category) {
		// TODO Auto-generated method stub
		categoryApplication.createCategory(category);
	}

	public CategoryService getCategoryApplication() {
		return categoryApplication;
	}

	public void setCategoryApplication(CategoryService categoryApplication) {
		this.categoryApplication = categoryApplication;
	}
	
		
}
