package com.degloba.domain.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @category Representa un handler d'un event de domini. 
 * Un handler d'events de domini consumeix events de domini d'un tipus concret. 
 * Són responsables del tractament de la recepció del mateix event de domini varies vegades (at-least-once
 * semantics).
 * <p>
 * Domain event handlers are typically part of domain services. A domain event handler method must accept a single
 * parameter of the domain event type handled.
 */
@Target(METHOD)
@Retention(RUNTIME)
@Documented
public @interface IDomainEventHandler {

}
