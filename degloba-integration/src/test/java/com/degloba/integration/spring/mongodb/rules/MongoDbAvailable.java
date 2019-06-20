package com.degloba.integration.spring.mongodb.rules;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotació utilitzada per a qualsevol mètode de test que requereixi un procés MongoDb en execució.
 * 
 * @author degloba
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MongoDbAvailable {

}
