package com.degloba.cqrs.command.handler;

import com.degloba.cqrs.command.impl.Command;

/**
 * 
 * @author degloba
 * 
 * @category Interficie : manipulador de {@link Command}
 *
 * @param <C> Command
 * @param <R> tipus de resultat - per asynchronous {@link Command} commands (asynchronous=true) hauria de ser {@link Void}
 */
public interface ICommandHandler<C, R> {

	/**
	 * Tracta el {@link Command}
	 * 
	 * @param command
	 * @return
	 */
    public R handle(C command);
}
