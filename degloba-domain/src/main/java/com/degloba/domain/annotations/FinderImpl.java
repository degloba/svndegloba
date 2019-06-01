package com.degloba.domain.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 
 * @author degloba
 * 
 * @category En el patró CQRS es creen diferents models per a comandes i per a consultes, de manera que es crea un model independent
 * per cada un d'ells
 *
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FinderImpl {

}