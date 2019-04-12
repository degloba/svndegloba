package com.degloba.ecommerce.sales.invoicing.domain.policies;

import com.degloba.domain.annotations.DomainPolicyImpl;


import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Tax;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.ProductType;
import com.degloba.persistence.domain.sharedkernel.Money;

/**
 * 
 * @author degloba
 *
 */
@DomainPolicyImpl
public class CrysisTaxPolicy implements ITaxPolicy{
	
	private double ratio;
	
	public CrysisTaxPolicy(double ratio){
		this.ratio = ratio;
	}
	
	@Override
	public Tax calculateTax(ProductType productType, Money net) {
		String desc = "sorry";				
		Money tax = net.multiplyBy(ratio);
		return new Tax(tax, desc);
	}

}
