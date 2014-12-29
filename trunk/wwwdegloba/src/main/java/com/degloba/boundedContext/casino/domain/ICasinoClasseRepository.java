package com.degloba.boundedContext.casino.domain;


import domain.seedwork.IRepository;
import domain.support.BaseAggregateRoot;


public interface ICasinoClasseRepository extends IRepository<BaseAggregateRoot> {

	public IRepository<BaseAggregateRoot> CreateRepository();

}
