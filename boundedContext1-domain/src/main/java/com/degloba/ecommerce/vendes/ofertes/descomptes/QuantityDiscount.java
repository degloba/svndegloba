package com.degloba.ecommerce.sales.offer.discounts;

import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Discount;
import com.degloba.ecommerce.sales.offer.domain.policies.DiscountPolicy;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;
import com.degloba.persistence.domain.sharedkernel.Money;


public class QuantityDiscount implements DiscountPolicy {
 /*
  * Ratio de Descompte
  */
	private double rebateRatio;
	
	private int mininalQuantity;
	
	public QuantityDiscount(double rebate, int mininalQuantity) {
		rebateRatio = rebate / 100;
		this.mininalQuantity = mininalQuantity;
	}

	@Override
	public Discount applyDiscount(Product product, int quantity, Money regularCost) {
		if (quantity >= mininalQuantity)
			return new Discount("over: " + quantity, regularCost.multiplyBy(rebateRatio));
		return null;
	}
}
