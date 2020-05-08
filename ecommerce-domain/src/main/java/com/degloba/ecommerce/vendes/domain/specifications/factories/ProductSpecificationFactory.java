package com.degloba.ecommerce.vendes.domain.specifications.factories;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.domain.specifications.DisjunctionSpecification;
import com.degloba.domain.specifications.Specification;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.domain.specifications.MateixaCategoriaSpecification;
import com.degloba.ecommerce.vendes.domain.specifications.NomSimilarSpecification;
import com.degloba.ecommerce.vendes.domain.specifications.PreuSimilarSpecification;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;

/**
 * @author degloba
 *
 * @category Factoria d'Specifications de Producte
 */
@DomainFactory
public class ProductSpecificationFactory {

	/**
	 * @category Crea (Fàbrica) una {@link Specification} de {@link Producte}
	 * @param client
	 * @param problematicProduct
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Specification<Producte> create(Client client,Producte problematicProduct) {
		
		// TODO explore domain rules, maybe use genetic algorithm to breed spec;)
		return new DisjunctionSpecification<Producte>(
					new PreuSimilarSpecification(problematicProduct.getPreu(), generateAcceptableDifference(client)), 
					new NomSimilarSpecification(problematicProduct.getNom()),
					new MateixaCategoriaSpecification(problematicProduct.getProducteType()));
	}

	private Money generateAcceptableDifference(Client client) {
		// TODO explore rules
		return new Money(7);
	}

}
