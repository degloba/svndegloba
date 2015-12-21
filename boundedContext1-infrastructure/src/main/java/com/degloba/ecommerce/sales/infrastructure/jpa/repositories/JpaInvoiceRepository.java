package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.JpaEntityRepository;

// Domain (ecommerce)
import com.degloba.ecommerce.sales.invoicing.domain.IInvoiceRepository;
import com.degloba.ecommerce.sales.invoicing.domain.Invoice;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaInvoiceRepository extends JpaEntityRepository<Invoice> implements IInvoiceRepository{

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
