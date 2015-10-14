package com.degloba.ecommerce.sales.invoicing;

import com.degloba.annotations.DomainPolicy;
import com.degloba.ecommerce.sales.productscatalog.ProductType;
import com.degloba.domain.sharedkernel.Money;

/**
 * Sample Policy
 * 
 * @author degloba
 *
 */
@DomainPolicy
public interface TaxPolicy {	

	/**
	 * calculates tax per product type based on net value
	 * @param productType
	 * @param net
	 * @return
	 */
	public Tax calculateTax(ProductType productType, Money net);

}
