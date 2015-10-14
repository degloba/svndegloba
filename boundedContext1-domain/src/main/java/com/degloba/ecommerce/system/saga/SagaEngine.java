package com.degloba.ecommerce.system.saga;

public interface SagaEngine {

    void handleSagasEvent(Object event);
}
