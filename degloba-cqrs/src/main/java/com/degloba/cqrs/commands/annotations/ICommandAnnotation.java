package com.degloba.cqrs.command.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author degloba
 *
 * @category {@link Annotation}
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ICommand {
	/**
	 * defineix si aquesta comanda es pot executar de manera asíncrona.
	 * <br>
	 * Si true llavors {@link ICommandHandler} ha de retornar void - en qualsevol altra cas llançarà una excepció 
	 * @return
	 */
    boolean asynchronous() default false;

    /**
     * defineix si aquesta comanda hauria de ser testejada per evitar que la mateixa comanda sigui enviada un altra vegada.<br>
     * If true than command class must implement equals and hashCode
     * @return
     */
    boolean unique() default false;

    /**
     * Si unique es true llavors aquesta propietat pot especificar el temps d'espera en milisegonds abans que 
     * la mateixa comanda pugui ser executada
     * 
     * @return
     */
    long uniqueStorageTimeout() default 0L;
}
