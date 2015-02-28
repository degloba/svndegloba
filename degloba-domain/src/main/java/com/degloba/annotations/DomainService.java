package com.degloba.annotations;

/**
 * 
 * @author degloba
 * 
 * @category Defineix la Interficie del Servei de Domini
 * No lligat al model de Domini (cap entitat)
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// SPRING
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DomainService {

}

/*@Service
//public interface DomainService <K,T extends Entitat>{
public @interface DomainService {
	
}*/
