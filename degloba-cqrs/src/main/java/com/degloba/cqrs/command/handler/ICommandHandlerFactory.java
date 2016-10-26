package com.degloba.cqrs.command.handler;

public interface ICommandHandlerFactory<C,R> {

    ICommandHandler<C,R> Create(C command);
    ICommandHandler<C,R> CreateByName(String name);

    void Release(ICommandHandler<C,R> handler);
}
