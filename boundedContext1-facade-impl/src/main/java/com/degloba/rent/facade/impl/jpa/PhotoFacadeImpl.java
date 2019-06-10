package com.degloba.rent.facade.impl.jpa;

import javax.inject.Inject;

import com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify.Foto;
import com.degloba.rent.application.services.IRentService;
import com.degloba.rent.facade.jpa.PhotoFacade;


public class PhotoFacadeImpl implements PhotoFacade {

    @Inject
    protected IRentService photoApplication;

    public PhotoFacadeImpl(IRentService application) {
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

	public IRentService getPhotoApplication() {
		return photoApplication;
	}

	public void setPhotoApplication(IRentService photoApplication) {
		this.photoApplication = photoApplication;
	}
	
		
}
