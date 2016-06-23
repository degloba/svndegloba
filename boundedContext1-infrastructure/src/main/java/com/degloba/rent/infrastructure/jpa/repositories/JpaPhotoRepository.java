package com.degloba.rent.infrastructure.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;

// Repository
import com.degloba.domain.JpaEntityRepository;

// Domain
import com.degloba.rent.domain.IPhotoRepository;
import com.degloba.rent.domain.Photo;

// Google App Engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaPhotoRepository extends JpaEntityRepository<Photo> implements IPhotoRepository{

	@Override
	public Photo load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Photo client) {
		// TODO Auto-generated method stub
		
	}

	
}
