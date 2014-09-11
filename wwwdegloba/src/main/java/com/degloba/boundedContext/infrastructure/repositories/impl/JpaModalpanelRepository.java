package com.degloba.boundedContext.infrastructure.repositories.impl;

// DDD
import infrastructure.repository.jpa.GenericJpaRepository;
import domain.annotations.DomainRepositoryImpl;



import com.degloba.boundedContext.domain.Modalpanel;
import com.degloba.boundedContext.domain.IModalpanelRepository;


@DomainRepositoryImpl
public class JpaModalpanelRepository extends GenericJpaRepository<Long,Modalpanel> implements IModalpanelRepository<Long>{

	@Override
	public Modalpanel load(Long modalpanelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modalpanel save(Modalpanel modalpanel) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public Modalpanel load(AggregateId id) {
		// TODO Auto-generated method stub
		return null;
	}
*/


}

