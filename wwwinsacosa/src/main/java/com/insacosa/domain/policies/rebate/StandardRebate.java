package com.insacosa.domain.policies.rebate;

import java.math.BigDecimal;

import ddd.domain.annotations.DomainPolicyImpl;
import ddd.domain.sharedkernel.Money;
import com.insacosa.domain.Product;
import com.insacosa.domain.RebatePolicy;

/**
 * Sample implementation of the Policy<br>
 * <br>
 * Calculate x% of the rebate if quantity of the product is greater than q
 * 
 * @author Slawek
 * 
 */
@DomainPolicyImpl
public class StandardRebate implements RebatePolicy {

	private BigDecimal rebateRatio;
	
	private int mininalQuantity;
	
	/**
	 * 
	 * @param rebate value of the rebate in % 
	 * @param mininalQuantity minimal quantity of the purchase that allows rebate
	 */
	public StandardRebate(double rebate, int mininalQuantity) {
		rebateRatio = new BigDecimal(rebate / 100);
		this.mininalQuantity = mininalQuantity;
	}

	@Override
	public Money calculateRebate(Product product, int quantity, Money regularCost){
		if (quantity >= mininalQuantity)
			return regularCost.multiplyBy(rebateRatio);
		return Money.ZERO;
	}

}
