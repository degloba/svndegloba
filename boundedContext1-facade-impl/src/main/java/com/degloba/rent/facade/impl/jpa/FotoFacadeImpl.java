package com.degloba.rent.facade.impl.jpa;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.facade.jpa.FotoFacade;


public class FotoFacadeImpl implements FotoFacade {

    @Inject
    protected ILloguerService fotoApplication;

    public FotoFacadeImpl(ILloguerService application) {
        this.fotoApplication = application;
    }
   
	public FotoFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPhoto(Foto foto) {
		// TODO Auto-generated method stub
		fotoApplication.creaFoto(foto);
	}

	public ILloguerService getFotoApplication() {
		return fotoApplication;
	}

	public void setFotoApplication(ILloguerService fotoApplication) {
		this.fotoApplication = fotoApplication;
	}
	
		
}
