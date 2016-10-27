package com.degloba.ecommerce.sales.invoicing.domain.policies;

import com.degloba.domain.annotations.DomainPolicy;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Tax;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.ProductType;
import com.degloba.domain.sharedkernel.Money;

/**
 * 
 * @author degloba
 *
 */
@DomainPolicy
public interface ITaxPolicy {	

	/**
	 * calculates tax per product type based on net value
	 * @param productType
	 * @param net
	 * @return
	 */
	public Tax calculateTax(ProductType productType, Money net);

}