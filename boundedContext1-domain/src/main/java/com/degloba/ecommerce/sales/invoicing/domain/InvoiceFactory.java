package com.degloba.ecommerce.sales.invoicing.domain;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.annotations.DomainFactory;
//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.domain.canonicalmodel.publishedlanguage.ClientData;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * 
 * @author degloba
 *
 */
@DomainFactory
public class InvoiceFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	public Invoice create(ClientData client){
		Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString());
		Invoice invoice = new Invoice(aggregateId, client);
		spring.autowireBean(invoice);
		return invoice;
	}
}
