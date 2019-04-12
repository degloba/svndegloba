package com.degloba.event.domain;

import java.io.Serializable;


/**
 * Un {@link DomainEvent} és únic, però no té cicle de vida.
 * La identitat pot ser explícita, per exemple, el número de seqüència d'un pagament,
 * o es podria derivar de diversos aspectes de l'esdeveniment, com ara on, quan i què
 * ha passat.
 */
public interface IDomainEvent<T> extends Serializable {
	
	  /**
	   * @param other The other domain event.
	   * @return <code>true</code> if the given domain event and this event are regarded as being the same event.
	   */
	  boolean sameEventAs(T other);
}
