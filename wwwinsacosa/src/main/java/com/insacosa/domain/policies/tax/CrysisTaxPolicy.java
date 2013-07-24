package com.insacosa.domain.policies.tax;

import java.math.BigDecimal;

import ddd.domain.annotations.DomainPolicyImpl;
import ddd.domain.sharedkernel.Money;
import com.insacosa.domain.Product.ProductType;
import com.insacosa.domain.Tax;
import com.insacosa.domain.TaxPolicy;

/**
 * Sample Policy impl<br> 
 * 
 * @author Slawek
 *
 */
@DomainPolicyImpl
public class CrysisTaxPolicy implements TaxPolicy{
				
	
	@Override
	public Tax calculateTax(ProductType productType, Money net) {
		BigDecimal ratio = BigDecimal.valueOf(0.4);
		String desc = "sorry";				
				
		Money tax = net.multiplyBy(ratio);
		
		return new Tax(tax, desc);
	}

}
