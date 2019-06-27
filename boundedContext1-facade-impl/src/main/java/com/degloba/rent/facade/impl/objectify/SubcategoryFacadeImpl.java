package com.degloba.rent.facade.impl.objectify;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.SubCategoria;
import com.degloba.lloguers.facade.objectify.SubcategoryFacade;


public class SubcategoryFacadeImpl implements SubcategoryFacade {

    @Inject
    protected ILloguerService subcategoryApplication;

    public SubcategoryFacadeImpl(ILloguerService application) {
        this.subcategoryApplication = application;
    }
   
	public SubcategoryFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createSubcategory(SubCategoria SubCategoria) {
		// TODO Auto-generated method stub
		subcategoryApplication.createSubcategory(SubCategoria);
	}

	public ILloguerService getSubcategoryApplication() {
		return subcategoryApplication;
	}

	public void setSubcategoryApplication(ILloguerService subcategoryApplication) {
		this.subcategoryApplication = subcategoryApplication;
	}
	
		
}
