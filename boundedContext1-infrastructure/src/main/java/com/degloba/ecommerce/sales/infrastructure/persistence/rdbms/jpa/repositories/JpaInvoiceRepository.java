package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
// Domain (ecommerce)
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.IInvoiceRepository;
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.Invoice;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaInvoiceRepository extends EntityRepository<Invoice> implements IInvoiceRepository{

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
