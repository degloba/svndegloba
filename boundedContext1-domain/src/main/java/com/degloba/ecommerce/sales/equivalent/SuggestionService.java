package com.degloba.ecommerce.sales.equivalent;

import java.util.List;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainService;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa.ISalesRepository;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;
//////////import com.degloba.ecommerce.sales.readmodel.offer.Offer;
import com.degloba.domain.specification.Specification;

/**
 * Sample Decision Support feature: suggests equivalent of the product based on client's habits. 
 * 
 * @author degloba
 *
 */
@DomainService
public class SuggestionService {

	@Inject
	private ISalesRepository productRepository;
	
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
