package com.degloba.casino.compres;

import com.degloba.annotations.DomainPolicy;

public class CompraPolicy {

	/**
	 * trivial discounting sample
	 *  
	 * @author degloba
	 */
	@DomainPolicy
	public interface DiscountPolicy {

		//public Discount applyDiscount(Product product, int quantity, Money reularCost);
	}
}
