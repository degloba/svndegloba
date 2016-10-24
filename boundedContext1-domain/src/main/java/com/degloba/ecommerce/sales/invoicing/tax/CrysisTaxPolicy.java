package com.degloba.ecommerce.sales.invoicing.tax;

import com.degloba.domain.annotations.DomainPolicyImpl;
import com.degloba.ecommerce.sales.invoicing.domain.Tax;
import com.degloba.ecommerce.sales.invoicing.domain.domainPolicies.ITaxPolicy;
import com.degloba.ecommerce.sales.productscatalog.domain.ProductType;
import com.degloba.domain.sharedkernel.Money;

/**
 * Sample Policy impl<br> 
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
