package com.degloba.rent.domain;

import com.degloba.domain.annotations.DomainRepository;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepository
public interface IPhotoRepository {

	public Photo load(Key id);

	public void save(Photo client);
}
