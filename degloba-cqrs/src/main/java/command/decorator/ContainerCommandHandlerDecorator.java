package command.decorator;

import command.handler.ICommandHandler;
import command.handler.ICommandHandlerFactory;

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

