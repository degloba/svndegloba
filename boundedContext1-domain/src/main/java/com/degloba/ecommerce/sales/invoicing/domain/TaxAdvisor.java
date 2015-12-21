package com.degloba.ecommerce.sales.invoicing.domain;

import com.degloba.domain.annotations.DomainService;
import com.degloba.ecommerce.sales.client.domain.Client;
import com.degloba.ecommerce.sales.invoicing.tax.DefaultTaxPolicy;

@DomainService
public class TaxAdvisor {

	public TaxPolicy suggestBestTax(Client client){
		//TODO explore domain rules
		return new DefaultTaxPolicy();
	}
}
