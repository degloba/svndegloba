package com.degloba.ecommerce.sales.offer.discounts;



import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Discount;
import com.degloba.ecommerce.sales.offer.domain.policies.DiscountPolicy;
import com.degloba.ecommerce.sales.productscatalog.domain.Product;
import com.degloba.domain.sharedkernel.Money;

public class QuantityDiscount implements DiscountPolicy{
	private double rebateRatio;
	
	private int mininalQuantity;
	
	/**
	 * 
	 * @param rebate value of the rebate in % 
	 * @param mininalQuantity minimal quantity of the purchase that allows rebate
	 */
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
