package com.degloba.ecommerce.sales.invoicing.domain;

import com.degloba.domain.annotations.DomainRepository;

/**
 * 
 * @author degloba
 *
 */
@DomainRepository
public interface IInvoiceRepository {

	/**
	 * @param invoice
	 */
	public void save(Invoice invoice);

}
