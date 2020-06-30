package com.degloba.ecommerce.vendes.domain.specifications;


import com.degloba.domain.specifications.CompositeSpecification;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.TipusProducte;

/**
 * 
 * @author degloba
 * 
 * @category Categoria similar</br>
 * Donat el {@link TipusProducte} d'un {@link Producte}, ens diu si es igual al {@link TipusProducte} 
 * del {@link Producte} candidat
 *
 */
public class MateixaCategoriaSpecification extends CompositeSpecification<Producte>{

	private TipusProducte tipusProducte;
	
	/**
	 * @category Categoria similar</br>
 	 * Donat el {@link TipusProducte} d'un {@link Producte}, ens diu si es igual al {@link TipusProducte} del {@link Producte} candidat
	 */
	public MateixaCategoriaSpecification(TipusProducte tipusProducte) {
		this.tipusProducte = tipusProducte;
	}

	@Override
	public boolean isSatisfiedBy(Producte candidate) {
		return candidate.getTipusProducte().equals(tipusProducte);
	}

}
