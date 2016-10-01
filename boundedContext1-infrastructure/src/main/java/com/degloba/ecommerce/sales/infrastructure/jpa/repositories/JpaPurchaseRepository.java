package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.persistence.rdbms.jpa.EntityRepository;
// Domain (ecommerce)
import com.degloba.ecommerce.sales.purchase.domain.IPurchaseRepository;
import com.degloba.ecommerce.sales.purchase.domain.Purchase;

// Google App Engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaPurchaseRepository extends EntityRepository<Purchase> implements IPurchaseRepository{

	@Override
	public Purchase load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Purchase entity) {
		// TODO Auto-generated method stub
		
	}

}
