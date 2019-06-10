package com.degloba.ecommerce.vendes.ofertes.domain.policies;

import com.degloba.domain.annotations.DomainPolicy;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Descompte;
import com.degloba.persistence.domain.sharedkernel.Money;


/**
 * Política de Descompte
 *  
 * @author degloba
 */
@DomainPolicy
public interface DescomptePolicy {

	public Descompte applyDiscount(Producte producte, int quantity, Money reularCost);
}
