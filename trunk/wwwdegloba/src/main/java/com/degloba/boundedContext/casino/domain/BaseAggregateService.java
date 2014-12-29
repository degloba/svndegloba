package com.degloba.boundedContext.casino.domain;

import domain.support.BaseAggregateRoot;

public class BaseAggregateService<E extends BaseAggregateRoot> implements IBaseAggregateService<E> {

	IBaseAggregateRootRepository i;
}
