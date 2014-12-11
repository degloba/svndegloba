package com.degloba.boundedContext.domain.casino;


import domain.seedwork.*;
import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;

public interface ICasinoClasseRepository extends IRepository<BaseAggregateRoot> {

	public IRepository<BaseAggregateRoot> CreateRepository();

}
