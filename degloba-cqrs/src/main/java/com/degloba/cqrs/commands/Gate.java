package com.degloba.cqrs.command;

/**
 * @category Punt d’accés principal a CQRS (Command Query Responsability Segregation)<br> 
 * Gestiona:
 * <ul>
 * <li>filtratge de {@link ICommand} duplicades
 * <li>encua {@link ICommand}s pel cas de les que son asíncrones
 * </ul>
 * 
 * @author degloba
 *
 */
public interface Gate {

	public abstract Object dispatch(Object command);

}