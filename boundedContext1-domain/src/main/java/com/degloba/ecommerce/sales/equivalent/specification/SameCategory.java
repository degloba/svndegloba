package com.degloba.ecommerce.sales.equivalent.specification;

import com.degloba.ecommerce.sales.productscatalog.domain.Product;
import com.degloba.ecommerce.sales.productscatalog.domain.ProductType;
import com.degloba.domain.specification.CompositeSpecification;

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
