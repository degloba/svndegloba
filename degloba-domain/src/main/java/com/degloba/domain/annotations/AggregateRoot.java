package com.degloba.domain.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author degloba
 * 
 * @category Segons el patró repositori, són els únics objectes que carrega el codi client del repositori.</br>
 * El repositori encapsula l'accés als objectes fills -des de la perspectiva de qui fa la crida, es carreguen automàticament, 
 * ja sigui al mateix temps que es carrega l'arrel o 
 * quan realment es necessiten (com si es tractés de "lazy loading").
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AggregateRoot {

}

