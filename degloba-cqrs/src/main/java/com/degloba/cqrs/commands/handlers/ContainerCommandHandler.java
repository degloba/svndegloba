package com.degloba.cqrs.commands.handlers;

import lombok.Value;

/**
 * 
 * @author degloba
 * 
 * @category 
 *
 * @param <TCommand>
 * @param <R>
 */
@Value
public class ContainerCommandHandler<TCommand,R> implements ICommandHandler<TCommand,R>
{
    private ICommandHandlerFactory<TCommand,R> _factory;
    
    public ContainerCommandHandler(ICommandHandlerFactory<TCommand,R> factory)
    {
        _factory = factory;
    }

	
    public R handle(TCommand command)
    {
        ICommandHandler<TCommand,R> handler = null;
        try
        {
            ICommandHandler<TCommand,R> commandHandler = _factory.CreateByName(command.getClass().getName());

            handler = (ICommandHandler<TCommand,R>)commandHandler;
            handler.handle(command);
        }
        finally
        {
            if (handler != null) 
                _factory.Release(handler);
        }
		return null;
    }
    
    
}

