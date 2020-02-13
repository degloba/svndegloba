package com.degloba.lloguers.facade.impl;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.impl.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.facade.ui.IFotoFacade;

import lombok.Value;

@Value
public class FotoFacadeImpl implements IFotoFacade {

    @Inject
    protected ILloguerService fotoApplication;

    public FotoFacadeImpl(ILloguerService application) {
        this.fotoApplication = application;
    }
   
	@Override
	public void creaFoto(Foto foto) {
		// TODO Auto-generated method stub
		fotoApplication.creaFoto(foto);
	}
		
}
