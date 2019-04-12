package com.degloba.ecommerce.sales.equivalent.specification;


import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.domain.specification.CompositeSpecification;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;

public class SimilarPrice extends CompositeSpecification<Product>{

	private Money min;
	private Money max;
	
	public SimilarPrice(Money price, Money acceptableDifference) {
		this.min = price.subtract(acceptableDifference);
		this.max = price.add(acceptableDifference);
	}

	@Override
	public boolean isSatisfiedBy(Product candidate) {		
		return candidate.getPrice().greaterThan(min) && candidate.getPrice().lessThan(max);
	}

}
