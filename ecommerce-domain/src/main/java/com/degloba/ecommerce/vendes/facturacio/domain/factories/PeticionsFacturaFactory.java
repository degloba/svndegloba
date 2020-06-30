package com.degloba.ecommerce.vendes.facturacio.domain.factories;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.compres.domain.persistence.rdbms.jpa.CompraArticle;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.PeticioFactura;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.RequestItem;
import com.degloba.persistence.rdbms.api.jpa.exceptions.DomainOperationException;


/**
 * @category Fàbrica de {@link peticioFactura} per totes les {@link Compra} d'un {@link Client}
 * 
 * @author degloba
 */
@DomainFactory
public class PeticionsFacturaFactory {

	public PeticioFactura create(Client client, Compra... compres) {
		PeticioFactura request = new PeticioFactura(client.generateSnapshot());
		
		for (Compra compra : compres) {
			if (! compra.isPagada())
				throw new DomainOperationException(compra.getAggregateId(), "Purchase is not paid");
			
			for (CompraArticle item : compra.getItems()) {
				request.add(new RequestItem(item.getProducteData(), item.getQuantitat(), item.getTotalCost()));
			}
		}
		
		return request;
	}

}
