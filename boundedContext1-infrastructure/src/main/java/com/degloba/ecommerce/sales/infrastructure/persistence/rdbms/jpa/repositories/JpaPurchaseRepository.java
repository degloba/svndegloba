package com.degloba.ecommerce.sales.infrastructure.persistence.rdbms.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
// Domain (ecommerce)
import com.degloba.ecommerce.sales.purchase.domain.IPurchaseRepository;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaPurchaseRepository extends EntityRepository implements IPurchaseRepository{


}
