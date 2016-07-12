package com.degloba.rent.domain.jpa;

import com.degloba.domain.annotations.DomainRepository;
import com.degloba.rent.domain.jpa.Photo;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepository
public interface IPhotoRepository {

	public Photo load(Key id);

	public void save(Photo client);
}
