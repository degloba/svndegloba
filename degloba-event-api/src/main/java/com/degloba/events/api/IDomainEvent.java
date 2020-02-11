package com.degloba.events.api;

/**
 * @category Un {@link DomainEvent} és únic, però no té cicle de vida.
 * La identitat pot ser explícita, per exemple, el número de seqüència d'un pagament,
 * o es podria derivar de diversos aspectes de l'event, com ara on, quan i què
 * ha passat.
 * 
 * @author degloba
 */
public interface IDomainEvent<T> extends IEvent {
	
	  /**
	   * @param altraEvent un altra event de domini.
	   * @return <code>true</code> si es considera que l’event de domini passat com a paràmetre 
	   * i aquest event són el mateix event.
	   */
	  boolean esIgualque(T altraEvent);
}
