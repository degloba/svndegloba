package com.degloba.ecommerce.sales.invoicing.domain.factories;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.InvoiceRequest;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.RequestItem;
import com.degloba.ecommerce.sales.purchase.domain.persistence.rdbms.jpa.Purchase;
import com.degloba.ecommerce.sales.purchase.domain.persistence.rdbms.jpa.PurchaseItem;
import com.degloba.persistence.domain.sharedkernel.exceptions.DomainOperationException;

/*
 * Fàbrica de Peticions de Factura
 * Per cada Compra
 */
@DomainFactory
public class InvoiceRequestFactory {

	public InvoiceRequest create(Client client, Purchase... purchases) {
		InvoiceRequest request = new InvoiceRequest(client.generateSnapshot());
		
		for (Purchase purchase : purchases) {
			if (! purchase.isPaid())
				throw new DomainOperationException(purchase.getAggregateId(), "Purchase is not paid");
			
			for (PurchaseItem item : purchase.getItems()) {
				request.add(new RequestItem(item.getProductData(), item.getQuantity(), item.getTotalCost()));
			}
		}
		
		return request;
	}

}
