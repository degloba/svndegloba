package com.degloba.rent.facade.impl.objectify;

import javax.inject.Inject;

import com.degloba.rent.application.objectify.api.SubcategoryService;
import com.degloba.rent.domain.objectify.Subcategory;
import com.degloba.rent.facade.objectify.SubcategoryFacade;


public class SubcategoryFacadeImpl implements SubcategoryFacade {

    @Inject
    protected SubcategoryService subcategoryApplication;

    public SubcategoryFacadeImpl(SubcategoryService application) {
        this.subcategoryApplication = application;
    }
   
	public SubcategoryFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createSubcategory(Subcategory Subcategory) {
		// TODO Auto-generated method stub
		subcategoryApplication.createSubcategory(Subcategory);
	}

	public SubcategoryService getSubcategoryApplication() {
		return subcategoryApplication;
	}

	public void setSubcategoryApplication(SubcategoryService subcategoryApplication) {
		this.subcategoryApplication = subcategoryApplication;
	}
	
		
}
