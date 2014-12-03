package com.degloba.boundedContext.domain.casino;


import domain.seedwork.*;
import domain.support.BaseEntity;

public interface ICasinoClasseRepository extends IRepository<BaseEntity> {

	public IRepository<BaseEntity> CreateRepository();

}
