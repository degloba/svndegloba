package com.degloba.infrastructure.sagas;

import com.degloba.infrastructure.sagas.impl.SagaInstance;

/**
 * @author Rafał Jamróz
 * 
 * @param <T>
 *            saga type
 * @param <D>
 *            saga data type
 */
public interface ISagaManager<T extends SagaInstance<D>, D> {

    void removeSaga(SagaInstance<D> sagaInstance);

    D createNewSagaData();
}
