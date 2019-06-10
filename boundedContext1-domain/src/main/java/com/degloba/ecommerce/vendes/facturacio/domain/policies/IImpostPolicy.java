package com.degloba.ecommerce.sales.invoicing.domain.policies;

import com.degloba.domain.annotations.DomainPolicy;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Tax;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.ProductType;
import com.degloba.persistence.domain.sharedkernel.Money;


/**
 * Política d'Impost
 * 
 * @author degloba
 *
 */
@DomainPolicy
public interface ITaxPolicy {	

	/**
	 * calcula impostos per tipus de producte segons el valor net
	 * 
	 * @param productType
	 * @param net
	 * @return
	 */
	public Tax calculateTax(ProductType productType, Money net);

}
