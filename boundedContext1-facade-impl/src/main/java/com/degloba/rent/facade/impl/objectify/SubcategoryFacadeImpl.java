package com.degloba.rent.facade.impl.objectify;

import javax.inject.Inject;

import com.degloba.rent.application.services.IRentService;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;
import com.degloba.rent.facade.objectify.SubcategoryFacade;


public class SubcategoryFacadeImpl implements SubcategoryFacade {

    @Inject
    protected IRentService subcategoryApplication;

    public SubcategoryFacadeImpl(IRentService application) {
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

	public IRentService getSubcategoryApplication() {
		return subcategoryApplication;
	}

	public void setSubcategoryApplication(IRentService subcategoryApplication) {
		this.subcategoryApplication = subcategoryApplication;
	}
	
		
}
