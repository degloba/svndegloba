package com.degloba.ecommerce.vendes.equivalent;

import java.util.List;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainService;
//////////import com.degloba.ecommerce.sales.readmodel.offer.Offer;
import com.degloba.domain.specification.Specification;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendaRepository;

/**
 * @category Servei de domini (funció) que dóna suport a la decisió de la selecció d'un producte.</br>
 * Suggereix un equivalent del producte basat en els hàbits del client.
 * 
 * @author degloba
 *
 */
@DomainService
public class SuggestionService {

	@Inject
	private IVendaRepository productRepository;
	
	//////////@Inject
	//////////private Offer offer;
	
	@Inject
	private ProductSpecificationFactory productSpecificationFactory;
	
	/**
	 * 
	 * 
	 * @param problematicProduct
	 * @param client
	 * @return
	 */
	public Producte suggestEquivalent(Producte problematicProduct, Client client) {
		List<Producte> expiringProducts = productRepository.findProductWhereBestBeforeExpiredIn(5);
		
		Specification<Producte> specification = productSpecificationFactory.create(client, problematicProduct);
		
		for (Producte suggestedProduct : expiringProducts) {
			if (specification.isSatisfiedBy(suggestedProduct))
				return suggestedProduct;
		}
		
		return null;
	}

}
