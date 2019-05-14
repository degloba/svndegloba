package com.degloba.cqrs.command.handler;

/**
 * 
 * @author degloba
 * 
 * @category Fàbrica de {@link ICommandHandler}
 *
 * @param <C> {@link Command}
 * @param <R> Resultat
 */
public interface ICommandHandlerFactory<C,R> {

    ICommandHandler<C,R> Create(C command);
    ICommandHandler<C,R> CreateByName(String name);

    void Release(ICommandHandler<C,R> handler);
}
