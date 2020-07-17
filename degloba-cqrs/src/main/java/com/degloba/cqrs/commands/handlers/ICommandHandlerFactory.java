package com.degloba.cqrs.commands.handlers;

/**
 * 
 * @author degloba
 * 
 * @category Fàbrica de {@link ICommandHandler}
 *
 * @param <C> {@link ICommandAnnotation}
 * @param <R> Resultat
 */
public interface ICommandHandlerFactory<C,R> {

    ICommandHandler<C,R> Create(C command);
    ICommandHandler<C,R> CreateByName(String name);

    void Release(ICommandHandler<C,R> handler);
}
