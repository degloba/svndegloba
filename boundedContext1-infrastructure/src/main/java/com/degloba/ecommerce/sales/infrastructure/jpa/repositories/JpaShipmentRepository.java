package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

//Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.domain.jpa.JpaEntityRepository;
// Domain (ecommerce)
import com.degloba.ecommerce.shipping.domain.IShipmentRepository;
import com.degloba.ecommerce.shipping.domain.Shipment;

// Google App Engine
import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaShipmentRepository extends JpaEntityRepository<Shipment> implements IShipmentRepository{

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
