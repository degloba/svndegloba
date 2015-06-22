/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.degloba.ecommerce.sales.invoicing.tax;

import com.degloba.annotations.DomainPolicyImpl;
import com.degloba.ecommerce.sales.invoicing.Tax;
import com.degloba.ecommerce.sales.invoicing.TaxPolicy;
import com.degloba.ecommerce.sales.productscatalog.ProductType;
import com.degloba.domain.sharedkernel.Money;

/**
 * Sample Policy impl<br> 
 * 
 * @author Slawek
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
