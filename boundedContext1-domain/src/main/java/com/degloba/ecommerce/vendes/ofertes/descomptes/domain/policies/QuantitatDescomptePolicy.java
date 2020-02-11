package com.degloba.ecommerce.vendes.ofertes.descomptes.domain.policies;

import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Descompte;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.persistence.domain.sharedkernel.Money;


public class QuantitatDescomptePolicy implements IDescomptePolicy {
 /*
  * Ratio de Descompte
  */
	private double rebateRatio;
	
	private int mininalQuantity;
	
	public QuantitatDescomptePolicy(double rebate, int mininalQuantity) {
		rebateRatio = rebate / 100;
		this.mininalQuantity = mininalQuantity;
	}

	@Override
	public Descompte aplicaDescompte(Producte producte, int quantitat, Money regularCost) {
		if (quantitat >= mininalQuantity)
			return new Descompte("over: " + quantitat, regularCost.multiplyBy(rebateRatio));
		return null;
	}
}
