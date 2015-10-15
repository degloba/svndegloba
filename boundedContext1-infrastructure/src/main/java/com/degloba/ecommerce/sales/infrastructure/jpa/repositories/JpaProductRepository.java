package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

import java.util.List;

import com.degloba.annotations.DomainRepositoryImpl;
import com.degloba.domain.EntityRepositoryJpa;
import com.degloba.ecommerce.sales.productscatalog.IProductRepository;
import com.degloba.ecommerce.sales.productscatalog.Product;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaProductRepository extends EntityRepositoryJpa<Product> implements IProductRepository{

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
