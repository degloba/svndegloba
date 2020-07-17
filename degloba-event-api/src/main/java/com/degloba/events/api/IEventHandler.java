package com.degloba.events.api;

/**
 * @category Event handler d'events de tipus {@link IEvent}
 * 
 * @author degloba
 */
public interface IEventHandler<T extends IEvent> {
	
	/**
	 * 
	 * @param event
	 * @return
	 * 
	 * @category retorna True si el {@link IEventHandler} pot gestionar (tractar) un {@link IEvent}
	 */
    boolean potGestionar(T event);

    /**
     * 
     * @param event
     * 
     * @category Gestiona (tracta) un event
     */
    void gestiona(T event);
}