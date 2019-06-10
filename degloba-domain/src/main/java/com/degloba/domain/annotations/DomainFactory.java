package com.degloba.domain.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

// SPRING
import org.springframework.stereotype.Component;


/**
 * @author degloba
 * 
 * @category Fàbrica d'entitats de domini
 * 
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainFactory {

}