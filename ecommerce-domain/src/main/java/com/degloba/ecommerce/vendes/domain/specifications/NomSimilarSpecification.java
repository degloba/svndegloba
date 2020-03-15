package com.degloba.ecommerce.vendes.domain.specifications;


import com.degloba.domain.specifications.CompositeSpecification;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;

/**
 * @author degloba
 * 
 * @category Nom similar</br>
 * Donat el nom d'un {@link Producte}, ens diu si es similar al nom del {@link Producte} candidat
 */
public class NomSimilarSpecification extends CompositeSpecification<Producte>{

	private String nom;
	
	/**
	 * Donat el nom d'un {@link Producte}, ens diu si es similar al nom del {@link Producte} candidat
	 * 
	 * @param nom
	 */
	public NomSimilarSpecification(String nom) {
		this.nom = nom;
	}

	@Override
	public boolean isSatisfiedBy(Producte candidate) {		
		return candidate.getNom().contains(nom) || candidate.getProducteType().toString().contains(nom);
	}

}
