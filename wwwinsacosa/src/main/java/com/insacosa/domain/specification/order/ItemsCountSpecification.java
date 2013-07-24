/**
 * 
 */
package com.insacosa.domain.specification.order;

import ddd.domain.annotations.DomainSpecification;
import ddd.domain.sharedkernel.specification.CompositeSpecification;
import com.insacosa.domain.Order;

/**
 * @author Slawek
 *
 */
@DomainSpecification
public class ItemsCountSpecification extends CompositeSpecification<Order>{
	
	private Integer min;
	
	private Integer max;
	
	

	public ItemsCountSpecification(Integer min, Integer max) {	
		this.min = min;
		this.max = max;
	}

	
	public ItemsCountSpecification(Integer max) {	
		this(null, max);
	}


	/* (non-Javadoc)
	 * @see ddd.domain.sharedcernel.specification.Specification#isSatisfiedBy(java.lang.Object)
	 */
	@Override
	public boolean isSatisfiedBy(Order order) {
		if (min != null){
			if (order.getItemsNumber() < min){
				return false;
			}
		}
		
		if (max != null){
			if (order.getItemsNumber() > max){
				return false;
			}
		}
		
		return true;
	}

}
