package com.degloba.rent.facade.impl.objectify;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.facade.objectify.CategoryFacade;

public class CategoryFacadeImpl implements CategoryFacade {

    @Inject
    protected ILloguerService lloguerService;

    public CategoryFacadeImpl(ILloguerService application) {
        this.lloguerService = application;
    }
   
	public CategoryFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createCategory(Categoria categoria) {
		// TODO Auto-generated method stub
		lloguerService.createCategory(categoria);
	}

	public ILloguerService getCategoryApplicationObjectify() {
		return lloguerService;
	}

	public void setCategoryApplicationObjectify(ILloguerService lloguerService) {
		this.lloguerService = lloguerService;
	}

	public ILloguerService getRentService() {
		return lloguerService;
	}

	public void setRentService(ILloguerService lloguerService) {
		this.lloguerService = lloguerService;
	}

		
}
