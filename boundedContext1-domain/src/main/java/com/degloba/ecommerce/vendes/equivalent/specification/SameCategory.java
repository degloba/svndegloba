package com.degloba.ecommerce.vendes.equivalent.specification;


import com.degloba.domain.specification.CompositeSpecification;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.TipusProducte;

/**
 * 
 * @author degloba
 * 
 * @category Categoria similar</br>
 * Donat el {@link TipusProducte} d'un {@link Producte}, ens diu si es igual al {@link TipusProducte} 
 * del {@link Producte} candidat
 *
 */
public class SameCategory extends CompositeSpecification<Producte>{

	private TipusProducte tipusProducte;
	
	/**
	 * @category Categoria similar</br>
 	 * Donat el {@link TipusProducte} d'un {@link Producte}, ens diu si es igual al {@link TipusProducte} del {@link Producte} candidat
	 */
	public SameCategory(TipusProducte tipusProducte) {
		this.tipusProducte = tipusProducte;
	}

	@Override
	public boolean isSatisfiedBy(Producte candidate) {
		return candidate.getProducteType().equals(tipusProducte);
	}

}
