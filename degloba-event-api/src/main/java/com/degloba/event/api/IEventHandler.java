package com.degloba.event.api;

/**
 * @category Interfície :  Handler d'events de tipus {@link IEvent}
 */
public interface IEventHandler<T extends IEvent> {
	
	/**
	 * 
	 * @param event
	 * @return
	 * 
	 * @category retorna True si el {@link IEventHandler} pot gestionar (tractar) un {@link IEvent}
	 */
    boolean canHandle(T event);

    /**
     * 
     * @param event
     * 
     * @category Gestiona (tracta) un event
     */
    void handle(T event);
}