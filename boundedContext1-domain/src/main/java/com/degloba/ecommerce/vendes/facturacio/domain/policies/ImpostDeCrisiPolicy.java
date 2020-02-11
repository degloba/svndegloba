package com.degloba.ecommerce.vendes.facturacio.domain.policies;

import com.degloba.domain.annotations.DomainPolicyImpl;
import com.degloba.ecommerce.vendes.facturacio.domain.persistence.rdbms.jpa.Tax;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.TipusProducte;
import com.degloba.persistence.domain.sharedkernel.Money;

/**
 * 
 * @author degloba
 * 
 * @category Política d'impost en el cas de "crisi"
 * Es calcula l'impost d'un tipus de producte {@link TipusProducte}
 *
 */
@DomainPolicyImpl
public class ImpostDeCrisiPolicy implements IImpostPolicy{
	
	private double ratio;
	
	public ImpostDeCrisiPolicy(double ratio){
		this.ratio = ratio;
	}
	
	@Override
	public Tax calculaImpost(TipusProducte tipusProducte, Money net) {
		String desc = "sorry";				
		Money tax = net.multiplyBy(ratio);
		return new Tax(tax, desc);
	}

}
