package com.degloba.events.bus;

import com.degloba.events.api.AbstractEventListener;
import com.degloba.events.api.IEvent;
import com.degloba.events.api.IEventListener;
import com.degloba.events.persistence.IEventStore;
import com.degloba.utils.Assert;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @category Bus d'events de tipus {@link IEvent}
 * 
 * @implNote Conté : un {@link IEventStore} i una llista de {@link IEventListener}S
 * 
 * @author degloba
 */
@Slf4j
public final class EventBus<T extends IEvent> implements IEventBus<T> {

    /**
     * Llista de {@link IEventListener}
     */
	@Getter
    private List<IEventListener<T>> eventListeners = new ArrayList<IEventListener<T>>();

    public EventBus(IEventStore<T> eventStore) {
    }

    public EventBus(@NonNull IEventStore<T> eventStore,@NonNull List<IEventListener<T>> listeners) {
        this.eventListeners = Collections.unmodifiableList(listeners);
    }


	@Override
	public void registra(AbstractEventListener<?> eventListener) {
		// TODO Auto-generated method stub
		
	}


}
