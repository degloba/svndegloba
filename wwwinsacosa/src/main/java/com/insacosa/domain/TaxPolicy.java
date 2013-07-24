package com.insacosa.domain;

import ddd.domain.annotations.DomainPolicy;
import ddd.domain.sharedkernel.Money;
import com.insacosa.domain.Product.ProductType;

/**
 * Sample Policy
 * 
 * @author Slawek
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
