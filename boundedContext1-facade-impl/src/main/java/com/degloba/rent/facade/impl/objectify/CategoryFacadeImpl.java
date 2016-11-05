package com.degloba.rent.facade.impl.objectify;

import javax.inject.Inject;

import com.degloba.rent.application.services.IRentService;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Category;
import com.degloba.rent.facade.objectify.CategoryFacade;


public class CategoryFacadeImpl implements CategoryFacade {

    @Inject
    protected IRentService rentService;

    public CategoryFacadeImpl(IRentService application) {
        this.rentService = application;
    }
   
	public CategoryFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createCategory(Category category) {
		// TODO Auto-generated method stub
		rentService.createCategory(category);
	}

	public IRentService getCategoryApplicationObjectify() {
		return rentService;
	}

	public void setCategoryApplicationObjectify(IRentService rentService) {
		this.rentService = rentService;
	}

	public IRentService getRentService() {
		return rentService;
	}

	public void setRentService(IRentService rentService) {
		this.rentService = rentService;
	}

		
}
