package com.degloba.domain.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// SPRING
import org.springframework.stereotype.Component;


/**
 * @author degloba
 * 
 * @category Defineix un servei de domini
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainService {

}

