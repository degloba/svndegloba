package com.degloba.rent.facade.impl;

import javax.inject.Inject;

import com.degloba.rent.application.api.PhotoService;
import com.degloba.rent.domain.Photo;
import com.degloba.rent.facade.PhotoFacade;


public class PhotoFacadeImpl implements PhotoFacade {

    @Inject
    protected PhotoService photoApplication;

    public PhotoFacadeImpl(PhotoService application) {
        this.photoApplication = application;
    }
   
	public PhotoFacadeImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPhoto(Photo photo) {
		// TODO Auto-generated method stub
		photoApplication.createPhoto(photo);
	}

	public PhotoService getPhotoApplication() {
		return photoApplication;
	}

	public void setPhotoApplication(PhotoService photoApplication) {
		this.photoApplication = photoApplication;
	}
	
		
}
