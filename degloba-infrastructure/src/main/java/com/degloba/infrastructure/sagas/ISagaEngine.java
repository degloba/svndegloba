package com.degloba.infrastructure.sagas;

import com.degloba.events.api.IEvent;

public interface ISagaEngine<T extends IEvent> {

    void handleSagasEvent(T event);
}
