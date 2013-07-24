/**
 * 
 */
package com.insacosa.domain.specification.order;

import ddd.domain.annotations.DomainSpecification;
import ddd.domain.sharedkernel.Money;
import ddd.domain.sharedkernel.specification.CompositeSpecification;
import com.insacosa.domain.Client;
import com.insacosa.domain.Order;

/**
 * Checks 
 * 
 * @author Slawek
 *
 */
@DomainSpecification
public class DebtorSpecification extends CompositeSpecification<Order>{
	
	private Money maxDebt;
	
	

	public DebtorSpecification(Money maxDebt) {	
		this.maxDebt = maxDebt;
	}

	/**
	 * No debt is allowed
	 */
	public DebtorSpecification() {	
		this(Money.ZERO);
	}
	/* (non-Javadoc)
	 * @see ddd.domain.sharedcernel.specification.Specification#isSatisfiedBy(java.lang.Object)
	 */
	@Override
	public boolean isSatisfiedBy(Order order) {
		Money debt = loadDebt(order.getClient());
		
		return debt.lessOrEquals(maxDebt);
	}


	/**
	 * @param client
	 * @return
	 */
	private Money loadDebt(Client client) {
		// TODO load debt using Repo/Service injected via constructor to this Spec
		return Money.ZERO;
	}

}
