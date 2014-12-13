package com.degloba.boundedContext.domain.modules.modalpanel;

import domain.annotations.DomainPolicy;

public class ModalpanelPolicy {

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
