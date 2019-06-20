package com.degloba.rent.facade.impl.objectify;

import javax.inject.Inject;

import com.degloba.lloguers.application.services.ILloguerService;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.lloguers.facade.objectify.PhotoFacade;


public class PhotoFacadeImpl implements PhotoFacade {

    @Inject
    protected ILloguerService photoApplication;

    public PhotoFacadeImpl(ILloguerService application) {
        this.photoApplication = application;
    }
   
	public PhotoFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPhoto(Foto foto) {
		// TODO Auto-generated method stub
		photoApplication.createPhoto(foto);
	}

	public ILloguerService getPhotoApplication() {
		return photoApplication;
	}

	public void setPhotoApplication(ILloguerService photoApplication) {
		this.photoApplication = photoApplication;
	}
	
		
}
