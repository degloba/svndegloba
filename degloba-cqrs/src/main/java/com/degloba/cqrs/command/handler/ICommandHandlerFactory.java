package com.degloba.cqrs.command.handler;

public interface ICommandHandlerFactory<C> {

    ICommandHandler<C> Create(C command);
    ICommandHandler<C> CreateByName(String name);

    void Release(ICommandHandler<C> handler);
}
