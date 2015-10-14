package com.degloba.ecommerce.sales.invoicing.tax;

import com.degloba.annotations.DomainPolicyImpl;
import com.degloba.ecommerce.sales.invoicing.Tax;
import com.degloba.ecommerce.sales.invoicing.TaxPolicy;
import com.degloba.ecommerce.sales.productscatalog.ProductType;
import com.degloba.domain.sharedkernel.Money;

/**
 * Sample Policy impl<br> 
 * 
 * @author degloba
 *
 */
@DomainPolicyImpl
public class CrysisTaxPolicy implements TaxPolicy{
	
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
