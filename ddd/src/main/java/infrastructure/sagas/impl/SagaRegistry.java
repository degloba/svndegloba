package infrastructure.sagas.impl;

import java.util.Collection;

import sagas.SagaInstance;
import sagas.SagaManager;

public interface SagaRegistry {

    Collection<SagaManager> getLoadersForEvent(Object event);

    SagaInstance createSagaInstance(Class<? extends SagaInstance> sagaType);
}
