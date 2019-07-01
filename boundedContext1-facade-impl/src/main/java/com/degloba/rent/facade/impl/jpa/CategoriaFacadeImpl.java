package com.degloba.rent.facade.impl.jpa;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.facade.jpa.CategoriaFacade;


public class CategoriaFacadeImpl implements CategoriaFacade {

    @Inject
    protected ILloguerService categoriaApplication;

    public CategoriaFacadeImpl(ILloguerService application) {
        this.categoriaApplication = application;
    }
   
	public CategoriaFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void creaCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		categoriaApplication.creaCategoria(categoria);
	}

	public ILloguerService getCategoriaApplication() {
		return categoriaApplication;
	}

	public void setCategoriaApplication(ILloguerService categoriaApplicationJpa) {
		this.categoriaApplication = categoriaApplication;
	}

	
	
		
}
