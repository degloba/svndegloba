package com.degloba.rent.facade.impl.jpa;

import javax.inject.Inject;

import com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.application.services.IRentService;
import com.degloba.rent.facade.jpa.CategoryFacade;


public class CategoryFacadeImpl implements CategoryFacade {

    @Inject
    protected IRentService categoryApplicationJpa;

    public CategoryFacadeImpl(IRentService application) {
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

	public IRentService getCategoryApplicationJpa() {
		return categoryApplicationJpa;
	}

	public void setCategoryApplicationJpa(IRentService categoryApplicationJpa) {
		this.categoryApplicationJpa = categoryApplicationJpa;
	}

	
	
		
}
