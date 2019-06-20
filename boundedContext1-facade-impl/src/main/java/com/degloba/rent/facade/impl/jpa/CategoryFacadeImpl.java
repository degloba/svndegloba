package com.degloba.rent.facade.impl.jpa;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.lloguers.facade.jpa.CategoryFacade;


public class CategoryFacadeImpl implements CategoryFacade {

    @Inject
    protected ILloguerService categoryApplicationJpa;

    public CategoryFacadeImpl(ILloguerService application) {
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

	public ILloguerService getCategoryApplicationJpa() {
		return categoryApplicationJpa;
	}

	public void setCategoryApplicationJpa(ILloguerService categoryApplicationJpa) {
		this.categoryApplicationJpa = categoryApplicationJpa;
	}

	
	
		
}
