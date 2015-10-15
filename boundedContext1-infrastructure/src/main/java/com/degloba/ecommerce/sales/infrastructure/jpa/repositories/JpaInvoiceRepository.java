package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

import com.degloba.annotations.DomainRepositoryImpl;
import com.degloba.domain.EntityRepositoryJpa;
import com.degloba.domain.GenericJpaRepository;
import com.degloba.ecommerce.crm.domain.Customer;
import com.degloba.ecommerce.crm.domain.ICustomerRepository;
import com.degloba.ecommerce.sales.invoicing.IInvoiceRepository;
import com.degloba.ecommerce.sales.invoicing.Invoice;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaInvoiceRepository extends EntityRepositoryJpa<Invoice> implements IInvoiceRepository{

/*	@Override
	public Invoice load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public void save(Invoice entity) {
		// TODO Auto-generated method stub
		
	}

}
