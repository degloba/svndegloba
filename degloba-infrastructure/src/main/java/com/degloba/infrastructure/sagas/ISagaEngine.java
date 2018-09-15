package com.degloba.infrastructure.sagas;

import com.degloba.event.api.IEvent;

public interface ISagaEngine<T extends IEvent> {

    void handleSagasEvent(T event);
}
