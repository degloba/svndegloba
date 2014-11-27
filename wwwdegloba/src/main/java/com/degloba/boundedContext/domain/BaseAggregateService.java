package com.degloba.boundedContext.domain;

import domain.support.BaseAggregateRoot;
import domain.support.BaseEntity;

public class BaseAggregateService<E extends BaseAggregateRoot> implements IBaseAggregateService<E> {

	IBaseAggregateRootRepository i;
}
