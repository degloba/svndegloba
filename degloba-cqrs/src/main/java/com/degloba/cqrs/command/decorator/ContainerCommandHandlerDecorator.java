package com.degloba.cqrs.command.decorator;

// CQRS
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.cqrs.command.handler.ICommandHandlerFactory;

public class ContainerCommandHandlerDecorator<TCommand> implements ICommandHandler<TCommand>
{
    private ICommandHandlerFactory<TCommand> _factory;
    public ContainerCommandHandlerDecorator(ICommandHandlerFactory<TCommand> factory)
    {
        _factory = factory;
    }

	
    public Object handle(TCommand command)
    {
        ICommandHandler<TCommand> handler = null;
        try
        {
            ICommandHandler<TCommand> commandHandler = _factory.CreateByName(command.getClass().getName());

            handler = (ICommandHandler<TCommand>)commandHandler;
            handler.handle(command);
        }
        finally
        {
            if (handler != null) 
                _factory.Release(handler);
        }
		return handler;
    }

    
    // getters - setters
	public ICommandHandlerFactory<TCommand> get_factory() {
		return _factory;
	}


    
    
}

