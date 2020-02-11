package com.degloba.domain.event;


import com.degloba.event.api.IEvent;
import com.degloba.event.bus.IEventBus;

/**
 * @author degloba
 * 
 * @category Bus d’events de domini.<br>
 * Hi ha varies parts :<br>
 * <ul>
 * <li>
 *       Publica un event al bus<br>
 * </li>
 * <li>
 *       Registre un subscriptor al bus<br>
 * </li>
 * </ul>
 */
public interface IDomainEventBus<T extends IEvent> extends IEventBus<T>{

    /**
     * L'event s'envia a tots els Subscriber
     * @param event Event de dominia a publicar
     */
    void publicaEvent(DomainEvent event);

    /**
     * Registre d'un mètode de subscripció d'events a tots els subscriptors registrats per rebre events al bus d'events.
     * @param subscriber Subscriptor. El subscriptor d'events ha d'implementar un o més instàncies d'una sub-classe 
     * of a field event com a únic paràmetre
     * And labeled asSubscribe approach.
     */
    void registreSubscriber(Object subscriber);

    /**
     * Deregistration a registered events to the event bus subscribers all subscription methods.
     * @param subscriber Event subscribers.
     */
    void unregisterSubscriber(Object subscriber);
}
