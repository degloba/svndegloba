package com.degloba.ecommerce.sales.invoicing;

import com.degloba.annotations.DomainService;
import com.degloba.ecommerce.sales.client.Client;
import com.degloba.ecommerce.sales.invoicing.tax.DefaultTaxPolicy;

@DomainService
public class TaxAdvisor {

	public TaxPolicy suggestBestTax(Client client){
		//TODO explore domain rules
		return new DefaultTaxPolicy();
	}
}
