package com.degloba.ecommerce.sales.client;

import com.degloba.annotations.DomainRepository;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.google.appengine.api.datastore.Key;

@DomainRepository
public interface IClientRepository {

	public Client load(Key id);

	public void save(Client client);
}
