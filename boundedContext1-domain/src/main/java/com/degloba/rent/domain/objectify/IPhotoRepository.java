package com.degloba.rent.domain.objectify;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.rent.domain.objectify.Photo;
import com.googlecode.objectify.Key;



@DomainRepository
public interface IPhotoRepository {

	public Photo load(Key<Photo> id);

	public void save(Photo client);
}
