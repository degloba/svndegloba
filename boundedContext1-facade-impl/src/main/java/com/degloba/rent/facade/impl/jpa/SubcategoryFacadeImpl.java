package com.degloba.rent.facade.impl.jpa;

import javax.inject.Inject;

import com.degloba.rent.application.jpa.api.PhotoService;
import com.degloba.rent.application.jpa.api.SubcategoryService;
import com.degloba.rent.domain.jpa.Photo;
import com.degloba.rent.domain.jpa.Subcategory;
import com.degloba.rent.facade.jpa.PhotoFacade;
import com.degloba.rent.facade.jpa.SubcategoryFacade;


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
