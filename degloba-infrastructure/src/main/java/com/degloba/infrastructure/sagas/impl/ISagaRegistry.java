package com.degloba.infrastructure.sagas.impl;

import java.util.Collection;

import com.degloba.infrastructure.sagas.ISagaManager;

public interface ISagaRegistry {

    Collection<ISagaManager> getLoadersForEvent(Object event);

    SagaInstance<?> createSagaInstance(Class<? extends SagaInstance> sagaType);
}
