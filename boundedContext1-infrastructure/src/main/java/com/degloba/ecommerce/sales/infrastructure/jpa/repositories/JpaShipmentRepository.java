package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

import com.degloba.annotations.DomainRepositoryImpl;
import com.degloba.domain.EntityRepositoryJpa;
import com.degloba.domain.GenericJpaRepository;

import com.degloba.ecommerce.shipping.domain.IShipmentRepository;
import com.degloba.ecommerce.shipping.domain.Shipment;
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
//public class JpaCustomerRepository extends GenericJpaRepository<Customer> implements CustomerRepository{
public class JpaShipmentRepository extends EntityRepositoryJpa<Shipment> implements IShipmentRepository{

	@Override
	public Shipment load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Shipment entity) {
		// TODO Auto-generated method stub
		
	}

}
