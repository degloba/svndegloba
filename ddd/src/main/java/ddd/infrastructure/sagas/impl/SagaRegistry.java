package ddd.infrastructure.sagas.impl;

import java.util.Collection;

import ddd.sagas.SagaInstance;
import ddd.sagas.SagaManager;

public interface SagaRegistry {

    Collection<SagaManager> getLoadersForEvent(Object event);

    SagaInstance<?> createSagaInstance(Class<? extends SagaInstance> sagaType);
}
