package com.degloba.cqrs.command;

/**
 * Punt d’accés principal a CQRS (Command Query Responsability Segregation)<br> 
 * Gestiona:
 * <ul>
 * <li>filtratge de {@link Command} duplicades
 * <li>encua {@link Command}s pel cas de les que son asíncrones
 * </ul>
 * 
 * @author degloba
 *
 */
public interface Gate {

	public abstract Object dispatch(Object command);

}