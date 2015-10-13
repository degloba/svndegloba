/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.degloba.ecommerce.sales.equivalent;

import java.util.List;

import javax.inject.Inject;

import com.degloba.annotations.DomainService;
import com.degloba.ecommerce.sales.client.Client;
import com.degloba.ecommerce.sales.productscatalog.Product;
import com.degloba.ecommerce.sales.productscatalog.IProductRepository;
//////////import com.degloba.ecommerce.sales.readmodel.offer.Offer;
import com.degloba.domain.specification.Specification;

/**
 * Sample Decision Support feature: suggests equivalent of the product based on client's habits. 
 * 
 * @author Slawek
 *
 */
@DomainService
public class SuggestionService {

	@Inject
	private IProductRepository productRepository;
	
	//////////@Inject
	//////////private Offer offer;
	
	@Inject
	private ProductSpecificationFactory productSpecificationFactory;
	
	public Product suggestEquivalent(Product problematicProduct, Client client) {
		List<Product> expiringProducts = productRepository.findProductWhereBestBeforeExpiredIn(5);
		
		Specification<Product> specification = productSpecificationFactory.create(client, problematicProduct);
		
		for (Product suggestedProduct : expiringProducts) {
			if (specification.isSatisfiedBy(suggestedProduct))
				return suggestedProduct;
		}
		
		return null;
	}

}
