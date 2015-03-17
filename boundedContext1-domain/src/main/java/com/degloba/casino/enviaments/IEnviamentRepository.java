package com.degloba.casino.enviaments;

import com.degloba.annotations.DomainRepository;
import com.degloba.domain.IBaseAggregateRootJpaRepository;


@DomainRepository
public interface IEnviamentRepository<K> extends IBaseAggregateRootJpaRepository<Enviament>  {

	
}
