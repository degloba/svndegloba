package com.degloba.ecommerce.sales.invoicing.tax;

import java.math.BigDecimal;

import com.degloba.domain.annotations.DomainPolicyImpl;

import com.degloba.ecommerce.sales.invoicing.domain.domainPolicies.ITaxPolicy;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Tax;
import com.degloba.ecommerce.sales.productscatalog.domain.ProductType;
import com.degloba.domain.sharedkernel.Money;

/**
 * Sample Policy impl<br> 
 * 
 * @author degloba
 *
 */
@DomainPolicyImpl
public class DefaultTaxPolicy implements ITaxPolicy{
				
	
	@Override
	public Tax calculateTax(ProductType productType, Money net) {
		BigDecimal ratio = null;
		String desc = null;
		
		switch (productType) {
		case DRUG:
			ratio = BigDecimal.valueOf(0.05);
			desc = "5% (D)";
			break;
		case FOOD:
			ratio = BigDecimal.valueOf(0.07);
			desc = "7% (F)";
			break;
		case STANDARD:
			ratio = BigDecimal.valueOf(0.23);
			desc = "23%";
			break;
			
		default:
			throw new IllegalArgumentException(productType + " not handled");
		}
				
		Money tax = net.multiplyBy(ratio);
		
		return new Tax(tax, desc);
	}

}
