package com.degloba.rent.facade.impl.jpa;

import javax.inject.Inject;

import com.degloba.rent.application.jpa.api.CategoryService;
import com.degloba.rent.domain.persistence.rdbms.jpa.Category;
import com.degloba.rent.facade.jpa.CategoryFacade;


public class CategoryFacadeImpl implements CategoryFacade {

    @Inject
    protected CategoryService categoryApplicationJpa;

    public CategoryFacadeImpl(CategoryService application) {
        this.categoryApplicationJpa = application;
    }
   
	public CategoryFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createCategory(Category category) {
		// TODO Auto-generated method stub
		categoryApplicationJpa.createCategory(category);
	}

	public CategoryService getCategoryApplicationJpa() {
		return categoryApplicationJpa;
	}

	public void setCategoryApplicationJpa(CategoryService categoryApplicationJpa) {
		this.categoryApplicationJpa = categoryApplicationJpa;
	}

	
	
		
}
