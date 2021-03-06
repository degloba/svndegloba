package com.degloba.domain.events;


import com.degloba.events.api.IEvent;
import com.degloba.events.bus.IEventBus;

/**
 * @author degloba
 * 
 * @category EventBus
 * 
 * 
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
     * @apiNote
     * 
     * L'event s'envia a tots els Subscriber
     * @param event Event de domini a publicar
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
    void unRegisterSubscriber(Object subscriber);
}
