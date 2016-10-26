package com.degloba.cqrs.command.decorator;

// CQRS
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.cqrs.command.handler.ICommandHandlerFactory;

public class ContainerCommandHandlerDecorator<TCommand,R> implements ICommandHandler<TCommand,R>
{
    private ICommandHandlerFactory<TCommand,R> _factory;
    public ContainerCommandHandlerDecorator(ICommandHandlerFactory<TCommand,R> factory)
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

    
    // getters - setters
	public ICommandHandlerFactory<TCommand,R> get_factory() {
		return _factory;
	}


    
    
}

