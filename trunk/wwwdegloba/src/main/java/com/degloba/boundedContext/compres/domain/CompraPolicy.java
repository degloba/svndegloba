package com.degloba.boundedContext.compres.domain;

import domain.annotations.DomainPolicy;

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
