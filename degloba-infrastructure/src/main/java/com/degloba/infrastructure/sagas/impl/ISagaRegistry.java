package com.degloba.infrastructure.sagas.impl;

import java.util.Collection;

import com.degloba.infrastructure.sagas.ISagaManager;

public interface ISagaRegistry<T extends SagaInstance<D>, D> {

    Collection<ISagaManager<T, D>> getLoadersForEvent(Object event);

    SagaInstance<D> createSagaInstance(Class<? extends SagaInstance<D>> sagaType);
}
