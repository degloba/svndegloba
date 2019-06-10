package com.degloba.ecommerce.sales.invoicing.domain.factories;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;


import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Invoice;
import com.degloba.persistence.domain.ClientData;


/**
 * Fabrica de Factures
 * 
 * @author degloba
 *
 */
@DomainFactory
public class InvoiceFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	public Invoice create(ClientData client){
		//Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString());
		Invoice invoice = new Invoice(1, client);
		spring.autowireBean(invoice);
		return invoice;
	}
}
