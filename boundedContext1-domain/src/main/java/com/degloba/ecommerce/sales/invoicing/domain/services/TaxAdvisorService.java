package com.degloba.ecommerce.sales.invoicing.domain.services;

import com.degloba.domain.annotations.DomainService;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.invoicing.domain.policies.DefaultTaxPolicy;
import com.degloba.ecommerce.sales.invoicing.domain.policies.ITaxPolicy;


@DomainService
public class TaxAdvisorService {

	public ITaxPolicy suggestBestTax(Client client){
		//TODO explore domain rules
		return new DefaultTaxPolicy();
	}
}
