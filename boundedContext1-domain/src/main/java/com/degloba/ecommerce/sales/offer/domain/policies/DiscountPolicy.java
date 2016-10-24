package com.degloba.ecommerce.sales.offer.domain.policies;

import com.degloba.domain.annotations.DomainPolicy;
import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Discount;
import com.degloba.ecommerce.sales.productscatalog.domain.Product;
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
