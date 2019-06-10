package com.degloba.ecommerce.vendes.facturacio.domain.factories;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Factura;
import com.degloba.persistence.domain.ClientData;


/**
 * Fabrica de Factures
 * 
 * @author degloba
 *
 */
@DomainFactory
public class FacturaFactory {

	@Inject
	private AutowireCapableBeanFactory spring;
	
	public Factura create(ClientData client){
		//Key aggregateId = KeyFactory.stringToKey( UUID.randomUUID().toString());
		Factura factura = new Factura(1, client);
		spring.autowireBean(factura);
		return factura;
	}
}
