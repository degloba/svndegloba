package com.degloba.ecommerce.system.saga;

/**
 * @author degloba
 * 
 * @param <T>
 *            saga type
 * @param <D>
 *            saga data type
 */
public interface SagaManager<T extends SagaInstance<D>, D> {

    void removeSaga(T saga);

    D createNewSagaData();
}
