package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
import com.degloba.ecommerce.sales.payment.domain.persistence.rdbms.jpa.IPaymentRepository;


/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaPaymentRepository extends EntityRepository implements IPaymentRepository{


}
