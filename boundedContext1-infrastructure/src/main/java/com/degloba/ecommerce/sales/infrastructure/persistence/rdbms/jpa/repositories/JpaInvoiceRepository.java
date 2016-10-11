package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;

// Domain (ecommerce)
import com.degloba.ecommerce.sales.invoicing.domain.persistence.rdbms.jpa.IInvoiceRepository;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaInvoiceRepository extends EntityRepository implements IInvoiceRepository{



}
