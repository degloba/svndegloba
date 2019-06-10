package com.degloba.ecommerce.vendes.facturacio.domain.policies;

import java.math.BigDecimal;

import com.degloba.domain.annotations.DomainPolicyImpl;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.TipusProducte;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Tax;
import com.degloba.persistence.domain.sharedkernel.Money;


/**
 * 
 * @author degloba
 * 
 * @category Política d'impost per defecte
 * Es calcula l'impost segons el tipus de producte {@link TipusProducte}
 *
 */
@DomainPolicyImpl
public class ImpostPerDefectePolicy implements IImpostPolicy{
				
	
	@Override
	public Tax calculateTax(TipusProducte tipusProducte, Money net) {
		BigDecimal ratio = null;
		String desc = null;
		
		switch (tipusProducte) {
		case DRUG:
			ratio = BigDecimal.valueOf(0.05);
			desc = "5% (D)";
			break;
		case FOOD:
			ratio = BigDecimal.valueOf(0.07);
			desc = "7% (F)";
			break;
		case STANDARD:
			ratio = BigDecimal.valueOf(0.23);
			desc = "23%";
			break;
			
		default:
			throw new IllegalArgumentException(tipusProducte + " not handled");
		}
				
		Money tax = net.multiplyBy(ratio);
		
		return new Tax(tax, desc);
	}

}
