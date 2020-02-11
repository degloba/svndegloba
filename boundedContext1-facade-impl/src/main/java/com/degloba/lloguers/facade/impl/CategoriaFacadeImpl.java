package com.degloba.rent.facade.impl.objectify;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.facade.objectify.CategoriaFacade;

public class CategoriaFacadeImpl implements CategoriaFacade {

    @Inject
    protected ILloguerService lloguerService;

    public CategoriaFacadeImpl(ILloguerService application) {
        this.lloguerService = application;
    }
   
	public CategoriaFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void creaCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		lloguerService.creaCategoria(categoria);
	}

	public ILloguerService getCategoriaApplicationObjectify() {
		return lloguerService;
	}

	public void setCategoriaApplicationObjectify(ILloguerService lloguerService) {
		this.lloguerService = lloguerService;
	}

	public ILloguerService getRentService() {
		return lloguerService;
	}

	public void setRentService(ILloguerService lloguerService) {
		this.lloguerService = lloguerService;
	}

		
}
