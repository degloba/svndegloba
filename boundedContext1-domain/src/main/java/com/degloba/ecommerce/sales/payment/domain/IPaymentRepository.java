package com.degloba.ecommerce.sales.payment.domain;

import com.degloba.annotations.DomainRepository;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.google.appengine.api.datastore.Key;

@DomainRepository
public interface IPaymentRepository {

	public Payment load(Key paymentId);
	
	public void save(Payment payment);
}
