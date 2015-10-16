package com.degloba.ecommerce.sales.purchase.domain;

import com.degloba.annotations.DomainRepository;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;

@DomainRepository
public interface IPurchaseRepository {

	Purchase load(Key orderId);

	void save(Purchase purchase);
}
