package com.degloba.lloguers.facade.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Categoria;
import com.degloba.lloguers.facade.ui.ICategoriaFacade;

import lombok.Value;

@Value
public class CategoriaFacadeImpl implements ICategoriaFacade {

    @Inject
    protected ILloguerService lloguerService;

    public CategoriaFacadeImpl(ILloguerService application) {
        this.lloguerService = application;
    }
   
	@Override
	public void creaCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		lloguerService.creaCategoria(categoria);
	}

		
}
