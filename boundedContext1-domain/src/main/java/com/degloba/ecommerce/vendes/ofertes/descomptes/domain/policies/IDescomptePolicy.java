package com.degloba.ecommerce.vendes.ofertes.descomptes.domain.policies;

import com.degloba.domain.annotations.DomainPolicy;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Descompte;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.persistence.domain.sharedkernel.Money;


/**
 * Política de {@link Descompte}
 *  
 * @author degloba
 */
@DomainPolicy
public interface IDescomptePolicy {

	public Descompte aplicaDescompte(Producte producte, int quantitat, Money reularCost);
}
