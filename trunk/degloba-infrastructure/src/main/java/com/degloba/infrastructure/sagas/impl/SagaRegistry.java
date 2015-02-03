package com.degloba.infrastructure.sagas.impl;

import java.util.Collection;

import com.degloba.sagas.SagaInstance;
import com.degloba.sagas.SagaManager;

public interface SagaRegistry {

    Collection<SagaManager> getLoadersForEvent(Object event);

    SagaInstance<?> createSagaInstance(Class<? extends SagaInstance> sagaType);
}
