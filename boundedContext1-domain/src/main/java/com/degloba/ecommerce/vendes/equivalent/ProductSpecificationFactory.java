package com.degloba.ecommerce.vendes.equivalent;

import com.degloba.domain.annotations.DomainFactory;
import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.domain.specification.DisjunctionSpecification;
import com.degloba.domain.specification.Specification;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.equivalent.specification.SameCategory;
import com.degloba.ecommerce.vendes.equivalent.specification.SimilarName;
import com.degloba.ecommerce.vendes.equivalent.specification.PreuSimilar;

/**
 * @author degloba
 *
 * @category 
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
	public Specification<Producte> create(Client client,	Producte problematicProduct) {
		
		// TODO explore domain rules, maybe use genetic algorithm to breed spec;)
		return new DisjunctionSpecification<Producte>(
					new PreuSimilar(problematicProduct.getPreu(), generateAcceptableDifference(client)), 
					new SimilarName(problematicProduct.getName()),
					new SameCategory(problematicProduct.getProductType()));
	}

	private Money generateAcceptableDifference(Client client) {
		// TODO explore rules
		return new Money(7);
	}

}
