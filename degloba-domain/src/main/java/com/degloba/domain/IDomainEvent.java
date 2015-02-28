package com.degloba.domain;

import java.io.Serializable;

/**
 * @author Slawek
 */

/**
 * A domain event is something that is unique, but does not have a lifecycle.
 * The identity may be explicit, for example the sequence number of a payment,
 * or it could be derived from various aspects of the event such as where, when and what
 * has happened.
 */
public interface IDomainEvent<T> extends Serializable {
	
	  /**
	   * @param other The other domain event.
	   * @return <code>true</code> if the given domain event and this event are regarded as being the same event.
	   */
	  boolean sameEventAs(T other);
}
