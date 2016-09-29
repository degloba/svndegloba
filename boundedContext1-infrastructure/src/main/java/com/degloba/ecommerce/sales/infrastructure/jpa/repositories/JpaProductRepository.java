package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

import java.util.List;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.jpa.JpaEntityRepository;
// Domain (ecommerce)
import com.degloba.ecommerce.sales.productscatalog.domain.IProductRepository;
import com.degloba.ecommerce.sales.productscatalog.domain.Product;

// Google App Engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaProductRepository extends JpaEntityRepository<Product> implements IProductRepository{

	@Override
	public Product load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductWhereBestBeforeExpiredIn(int days) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product load(Class<Product> class1, Key productId) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public void save(Product entity) {
		// TODO Auto-generated method stub
		
	}*/

}
