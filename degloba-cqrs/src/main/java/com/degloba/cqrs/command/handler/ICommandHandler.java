package com.degloba.cqrs.command.handler;

import com.degloba.cqrs.command.impl.Command;

/**
 * 
 * @author degloba
 *
 * @param <C> command
 * @param <R> result type - for asynchronous {@link Command}commands (asynchronous=true) should be {@link Void}
 */
public interface ICommandHandler<C, R> {

    public R handle(C command);
}
