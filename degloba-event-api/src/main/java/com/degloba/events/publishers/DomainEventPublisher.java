package com.degloba.events.publishers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.degloba.events.api.IDomainEvent;
import com.degloba.events.api.IEvent;
import com.degloba.events.api.IEventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @category Publicador d'events de domini de tipus {@link IEvent}<p/>
 * <ul>
 * <li>
 * Es un {@link Component} Spring.
 * </li>
 * <li>
 * Els handlers estan en memoria ({@link HashSet})
 * </li>
 * 
 * @author degloba
 */
@Slf4j
@Component
public class DomainEventPublisher<T extends IEvent> implements IDomainEventPublisher<IDomainEvent<T>>, IApplicationEventPublisher<T> {
    
	/**
	 * 
	 */
    private Set<IEventHandler<T>> eventHandlers = new HashSet<IEventHandler<T>>();

    /**
     * 
     * @param handler
     * 
     * @category Afegeix un {@link IEventHandler} en la colecció {@link eventHandlers}
     */
    public void registerEventHandler(IEventHandler<T> handler) {
        eventHandlers.add(handler);
        // new SpringEventHandler(eventType, beanName, method));
    }


    /**
     * @param event
     * 
     * @category Cerca dins la colecció {@link eventHandlers} un {@link IEventHandler} que pot tractar el 
     * {@link IEvent} i executa el seu mètode Handle
     */
    protected void doPublica(T event) {
        for (IEventHandler<T> handler : new ArrayList<IEventHandler<T>>(eventHandlers)) {
            if (handler.potGestionar(event)) {
                try {
                    handler.gestiona(event);
                } catch (Exception e) {
                    log.error("event handling error", e);
                }
            }
        }
    }

    /**
     * Publica l'event
     */
	@Override
	public void publica(T event) {
		doPublica(event);
	}


	@Override
	public void publica(IDomainEvent<T> event) {
		// TODO Auto-generated method stub

	}




}
