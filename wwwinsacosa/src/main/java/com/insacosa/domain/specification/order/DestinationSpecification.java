/**
 * 
 */
package com.insacosa.domain.specification.order;

import java.util.Locale;

import ddd.domain.annotations.DomainSpecification;
import ddd.domain.sharedkernel.specification.CompositeSpecification;
import com.insacosa.domain.Order;

/**
 * Checks 
 * 
 * @author Slawek
 *
 */
@DomainSpecification
public class DestinationSpecification extends CompositeSpecification<Order>{	

	private Locale[] allowedLocale;
	
	public DestinationSpecification(Locale...allowedLocale) {	
		this.allowedLocale = allowedLocale;
	}


	/* (non-Javadoc)
	 * @see ddd.domain.sharedcernel.specification.Specification#isSatisfiedBy(java.lang.Object)
	 */
	@Override
	public boolean isSatisfiedBy(Order order) {
		//TODO check if order destination is on allowedLocale
		return true;
	}


}
