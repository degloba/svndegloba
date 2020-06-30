package com.degloba.ecommerce.vendes.domain.services;

import java.util.List;

import javax.inject.Inject;

import com.degloba.domain.annotations.DomainService;
import com.degloba.domain.specifications.Specification;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendaRepository;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.domain.specifications.factories.ProductSpecificationFactory;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;

/**
 * @category Servei de domini que dóna suport a la decisió de la selecció d'un producte.</br>
 * Suggereix un producte equivalent basat en els hàbits del client.
 * 
 * @author degloba
 *
 */
@DomainService
public class SuggerimentService {

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
	public Producte suggerirProducteEquivalent(Producte problematicProduct, Client client) {
		List<Producte> expiringProducts = productRepository.findProductWhereBestBeforeExpiredIn(5);
		
		Specification<Producte> specification = productSpecificationFactory.create(client, problematicProduct);
		
		for (Producte suggestedProduct : expiringProducts) {
			if (specification.isSatisfiedBy(suggestedProduct))
				return suggestedProduct;
		}
		
		return null;
	}

}
