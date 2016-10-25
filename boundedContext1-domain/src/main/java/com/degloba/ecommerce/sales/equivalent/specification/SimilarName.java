package com.degloba.ecommerce.sales.equivalent.specification;


import com.degloba.domain.specification.CompositeSpecification;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;

public class SimilarName extends CompositeSpecification<Product>{

	private String name;
	
	public SimilarName(String name) {
		this.name = name;
	}

	@Override
	public boolean isSatisfiedBy(Product candidate) {		
		return candidate.getName().contains(name) || candidate.getProductType().toString().contains(name);
	}

}
