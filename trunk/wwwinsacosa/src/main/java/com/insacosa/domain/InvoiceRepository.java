package com.insacosa.domain;

import ddd.domain.annotations.DomainRepository;

/**
 * 
 * @author Slawek
 *
 */
@DomainRepository
public interface InvoiceRepository {

	/**
	 * @param invoice
	 */
	public Invoice save(Invoice invoice);

}
