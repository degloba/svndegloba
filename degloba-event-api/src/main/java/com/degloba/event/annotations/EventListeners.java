package com.degloba.event.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @category Anotació : EventListeners
 * 
 * @author degloba
 * 
 */
@Component
@Target(ElementType.TYPE)
public @interface EventListeners {

}