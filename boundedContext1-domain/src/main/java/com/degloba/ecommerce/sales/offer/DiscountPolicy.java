package com.degloba.ecommerce.sales.offer;

import com.degloba.annotations.DomainPolicy;
import com.degloba.ecommerce.sales.productscatalog.Product;
import com.degloba.domain.sharedkernel.Money;

/**
 * trivial discounting sample
 *  
 * @author degloba
 */
@DomainPolicy
public interface DiscountPolicy {

	public Discount applyDiscount(Product product, int quantity, Money reularCost);
}
