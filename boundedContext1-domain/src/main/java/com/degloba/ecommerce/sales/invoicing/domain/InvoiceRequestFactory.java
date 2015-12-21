package com.degloba.ecommerce.sales.invoicing.domain;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.ecommerce.sales.client.domain.Client;
import com.degloba.ecommerce.sales.purchase.domain.Purchase;
import com.degloba.ecommerce.sales.purchase.domain.PurchaseItem;
import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;

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
