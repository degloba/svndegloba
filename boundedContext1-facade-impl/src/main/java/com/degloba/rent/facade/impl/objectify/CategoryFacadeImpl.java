package com.degloba.rent.facade.impl.objectify;

import javax.inject.Inject;

import com.degloba.rent.application.objectify.api.CategoryService;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.facade.objectify.CategoryFacade;


public class CategoryFacadeImpl implements CategoryFacade {

    @Inject
    protected CategoryService categoryApplicationObjectify;

    public CategoryFacadeImpl(CategoryService application) {
        this.categoryApplicationObjectify = application;
    }
   
	public CategoryFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createCategory(Category category) {
		// TODO Auto-generated method stub
		categoryApplicationObjectify.createCategory(category);
	}

	public CategoryService getCategoryApplicationObjectify() {
		return categoryApplicationObjectify;
	}

	public void setCategoryApplicationObjectify(CategoryService categoryApplicationObjectify) {
		this.categoryApplicationObjectify = categoryApplicationObjectify;
	}

	
	
		
}
