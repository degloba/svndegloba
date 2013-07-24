package com.insacosa.domain;

import ddd.domain.annotations.DomainPolicy;
import ddd.domain.sharedkernel.Money;

/**
 * 
 * @author Slawek
 *
 */
@DomainPolicy
public interface RebatePolicy {

	public Money calculateRebate(Product product, int quantity, Money regularCost);

}
