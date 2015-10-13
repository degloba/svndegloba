package com.degloba.organisation.domain.client;

import com.degloba.annotations.DomainRepository;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepository
public interface IClientRepository {

	public Client load(Key id);

	public void save(Client client);
}
