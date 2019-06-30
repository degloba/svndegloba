package com.degloba.ecommerce.vendes.facturacio.domain.factories;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.CompraItem;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.PeticioFactura;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.RequestItem;
import com.degloba.persistence.rdbms.jpa.exceptions.DomainOperationException;


/**
 * @category Fàbrica de Peticions de Factura
 * Per cada Compra
 */
@DomainFactory
public class PeticionsFacturaFactory {

	public PeticioFactura create(Client client, Compra... purchases) {
		PeticioFactura request = new PeticioFactura(client.generateSnapshot());
		
		for (Compra compra : purchases) {
			if (! compra.isPaid())
				throw new DomainOperationException(compra.getAggregateId(), "Purchase is not paid");
			
			for (CompraItem item : compra.getItems()) {
				request.add(new RequestItem(item.getProductData(), item.getQuantitat(), item.getTotalCost()));
			}
		}
		
		return request;
	}

}
