package com.degloba.ecommerce.sales.infrastructure.jpa.repositories;

// Domain
import com.degloba.domain.annotations.DomainRepositoryImpl;
import com.degloba.ecommerce.sales.client.domain.Client;
import com.degloba.ecommerce.sales.client.domain.IClientRepository;
import com.degloba.domain.jpa.JpaEntityRepository;


import com.google.appengine.api.datastore.Key;

/**
 * @author degloba
 *
 */
@DomainRepositoryImpl
public class JpaClientRepository extends JpaEntityRepository<Client> implements IClientRepository{

	@Override
	public Client load(Key id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Client entity) {
		// TODO Auto-generated method stub
		
	}

}
