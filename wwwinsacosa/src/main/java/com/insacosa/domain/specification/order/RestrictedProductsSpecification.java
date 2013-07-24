/**
 * 
 */
package com.insacosa.domain.specification.order;

import ddd.domain.annotations.DomainSpecification;
import ddd.domain.sharedkernel.specification.CompositeSpecification;
import com.insacosa.domain.Order;
import com.insacosa.domain.OrderedProduct;
import com.insacosa.domain.Product;

/**
 * Checks 
 * 
 * @author Slawek
 *
 */
@DomainSpecification
public class RestrictedProductsSpecification extends CompositeSpecification<Order>{	

	private Product[] restrictedProducts;
	
	public RestrictedProductsSpecification(Product... restrictedProducts) {	
		this.restrictedProducts = restrictedProducts;
	}


	/* (non-Javadoc)
	 * @see ddd.domain.sharedcernel.specification.Specification#isSatisfiedBy(java.lang.Object)
	 */
	@Override
	public boolean isSatisfiedBy(Order order) {
		for (OrderedProduct product : order.getOrderedProducts()){
			if(isWeaponOfMassDestruction(product))
				return false;
		}
		
		return true;
	}


	/**
	 * @param product 
	 * @return
	 */
	private boolean isWeaponOfMassDestruction(OrderedProduct product) {
		// TODO Auto-generated method stub
		return false;
	}


}
