package com.degloba.rent.application.objectify.impl;


import javax.inject.Inject;

import com.degloba.rent.application.objectify.api.PhotoService;
import com.degloba.rent.domain.objectify.IPhotoRepository;
import com.degloba.rent.domain.objectify.Photo;


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
