/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.degloba.ecommerce.sales.invoicing;

import com.degloba.annotations.DomainFactory;
import com.degloba.ecommerce.sales.client.Client;
import com.degloba.ecommerce.sales.purchase.Purchase;
import com.degloba.ecommerce.sales.purchase.PurchaseItem;
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
