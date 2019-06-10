package com.degloba.ecommerce.vendes.equivalent.specification;


import com.degloba.domain.specification.CompositeSpecification;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;

/**
 * @author degloba
 * 
 * @category Nom similar</br>
 * Donat el nom d'un {@link Producte}, ens diu si es similar al nom del {@link Producte} candidat
 */
public class SimilarName extends CompositeSpecification<Producte>{

	private String name;
	
	/**
	 * Donat el nom d'un {@link Producte}, ens diu si es similar al nom del {@link Producte} candidat
	 * 
	 * @param name
	 */
	public SimilarName(String name) {
		this.name = name;
	}

	@Override
	public boolean isSatisfiedBy(Producte candidate) {		
		return candidate.getName().contains(name) || candidate.getProductType().toString().contains(name);
	}

}
