package com.degloba.ecommerce.vendes.ofertes.descomptes;

import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Descompte;
import com.degloba.ecommerce.vendes.ofertes.domain.policies.DescomptePolicy;
import com.degloba.persistence.domain.sharedkernel.Money;


public class QuantityDiscount implements DescomptePolicy {
 /*
  * Ratio de Descompte
  */
	private double rebateRatio;
	
	private int mininalQuantity;
	
	public QuantityDiscount(double rebate, int mininalQuantity) {
		rebateRatio = rebate / 100;
		this.mininalQuantity = mininalQuantity;
	}

	@Override
	public Descompte applyDiscount(Producte producte, int quantitat, Money regularCost) {
		if (quantitat >= mininalQuantity)
			return new Descompte("over: " + quantitat, regularCost.multiplyBy(rebateRatio));
		return null;
	}
}
