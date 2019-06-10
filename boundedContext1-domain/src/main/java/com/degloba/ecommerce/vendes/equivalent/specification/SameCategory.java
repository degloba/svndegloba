package com.degloba.ecommerce.sales.equivalent.specification;


import com.degloba.domain.specification.CompositeSpecification;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.ProductType;

public class SameCategory extends CompositeSpecification<Product>{

	private ProductType productType;
	
	public SameCategory(ProductType productType) {
		this.productType = productType;
	}

	@Override
	public boolean isSatisfiedBy(Product candidate) {
		return candidate.getProductType().equals(productType);
	}

}
