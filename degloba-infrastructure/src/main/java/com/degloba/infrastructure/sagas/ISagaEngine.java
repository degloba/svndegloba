package com.degloba.infrastructure.sagas;

public interface ISagaEngine {

    void handleSagasEvent(Object event);
}
