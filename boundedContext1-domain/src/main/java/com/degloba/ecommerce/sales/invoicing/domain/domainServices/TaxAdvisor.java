package com.degloba.ecommerce.sales.invoicing.domain.domainServices;

import com.degloba.domain.annotations.DomainService;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.invoicing.domain.domainPolicies.ITaxPolicy;
import com.degloba.ecommerce.sales.invoicing.tax.DefaultTaxPolicy;

@DomainService
public class TaxAdvisor {

	public ITaxPolicy suggestBestTax(Client client){
		//TODO explore domain rules
		return new DefaultTaxPolicy();
	}
}
