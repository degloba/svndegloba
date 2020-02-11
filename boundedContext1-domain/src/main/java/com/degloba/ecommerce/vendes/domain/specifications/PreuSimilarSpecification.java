package com.degloba.ecommerce.vendes.equivalent.specification;


import com.degloba.persistence.domain.sharedkernel.Money;
import com.degloba.domain.specification.CompositeSpecification;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;

/**
 * @author degloba
 *
 * @category Preu similar</br>
 * Donat el preu d'un {@link Producte} i una diferència de preu acceptable, ens diu si un {@link Producte} 
 * candidat satisfà la condició
 */
public class PreuSimilar extends CompositeSpecification<Producte>{

	private Money min;
	private Money max;
	
	/**
	 * Donat el preu d'un {@link Producte} i una diferència de preu acceptable, ens diu si un {@link Producte} 
	 * candidat satisfà la condició
	 * 
	 * @param price
	 * @param acceptableDifference
	 */
	public PreuSimilar(Money price, Money acceptableDifference) {
		this.min = price.subtract(acceptableDifference);
		this.max = price.add(acceptableDifference);
	}

	@Override
	public boolean isSatisfiedBy(Producte candidate) {		
		return candidate.getPreu().greaterThan(min) && candidate.getPreu().lessThan(max);
	}

}
