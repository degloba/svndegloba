package com.degloba.cqrs.commands.handlers;

import com.degloba.cqrs.commands.ICommand;

/**
 * 
 * @author degloba
 * 
 * @category manipulador de {@link ICommand}
 *
 * @param <C> Command
 * @param <R> tipus de resultat - per asynchronous {@link ICommand} commands (asynchronous=true) hauria de ser {@link Void}
 */
public interface ICommandHandler<C, R> {

	/**
	 * Tracta el {@link ICommand}
	 * 
	 * @param command
	 * @return
	 */
    public R handle(C command);
}
