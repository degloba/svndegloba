package com.degloba.ecommerce.system.saga.impl;

import java.util.Collection;

import com.degloba.ecommerce.system.saga.SagaInstance;
import com.degloba.ecommerce.system.saga.SagaManager;

public interface SagaRegistry {

    @SuppressWarnings("rawtypes")
	Collection<SagaManager> getLoadersForEvent(Object event);

    @SuppressWarnings("rawtypes")
	SagaInstance createSagaInstance(Class<? extends SagaInstance> sagaType);
}
