package com.degloba.ecommerce.sales.equivalent;

import com.degloba.annotations.DomainFactory;
import com.degloba.ecommerce.sales.client.Client;
import com.degloba.ecommerce.sales.equivalent.specification.SameCategory;
import com.degloba.ecommerce.sales.equivalent.specification.SimilarName;
import com.degloba.ecommerce.sales.equivalent.specification.SimilarPrice;
import com.degloba.ecommerce.sales.productscatalog.Product;
import com.degloba.domain.sharedkernel.Money;
import com.degloba.domain.specification.DisjunctionSpecification;
import com.degloba.domain.specification.Specification;

@DomainFactory
public class ProductSpecificationFactory {

	@SuppressWarnings("unchecked")
	public Specification<Product> create(Client client,
			Product problematicProduct) {
		// TODO explore domain rules, maybe use genetic algorithm to breed spec;)
		return new DisjunctionSpecification<Product>(
					new SimilarPrice(problematicProduct.getPrice(), generateAcceptableDifference(client)), 
					new SimilarName(problematicProduct.getName()),
					new SameCategory(problematicProduct.getProductType()));
	}

	private Money generateAcceptableDifference(Client client) {
		// TODO explore rules
		return new Money(7);
	}

}
