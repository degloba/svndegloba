package com.degloba.infrastructure.sagas;

public interface SagaEngine {

    void handleSagasEvent(Object event);
}
