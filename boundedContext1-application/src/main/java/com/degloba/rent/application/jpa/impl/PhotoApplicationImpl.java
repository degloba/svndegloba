package com.degloba.rent.application.jpa.impl;


// Domain

import com.degloba.rent.application.jpa.api.PhotoService;
import com.degloba.rent.domain.jpa.IPhotoRepository;
import com.degloba.rent.domain.jpa.Photo;

import javax.inject.Inject;


public class PhotoApplicationImpl implements PhotoService {
	
	@Inject
	private IPhotoRepository photoRepository;

  
	public PhotoApplicationImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPhoto(Photo photo) {
		// TODO Auto-generated method stub
		
		photoRepository.save(photo);
		
	}

	public PhotoApplicationImpl(IPhotoRepository photoRepository) {
		super();
		this.photoRepository = photoRepository;
	}
	
}