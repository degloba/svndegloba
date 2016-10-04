package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify;

import com.degloba.domain.annotations.DomainRepository;

import com.googlecode.objectify.Key;


@DomainRepository
public interface IPhotoRepository {

	public Photo load(Key<Photo> id);

	public void save(Photo client);
}
