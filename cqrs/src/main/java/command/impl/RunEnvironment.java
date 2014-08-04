package command.impl;

import javax.inject.Inject;

// SPRING
import org.springframework.stereotype.Component;

// CQRS
import command.handler.ICommandHandler;

/**
 * @author degloba
 */
@Component
public class RunEnvironment {

	public interface HandlersProvider{
		ICommandHandler<Object> getHandler(Object command);
	}
	
	@Inject
	private HandlersProvider handlersProvider;
	
	public Object run(Object command) {		
		ICommandHandler<Object> handler = handlersProvider.getHandler(command);
		
		//You can add Your own capabilities here: dependency injection, security, transaction management, logging, profiling, spying, storing commands, etc
		
		Object result = handler.handle(command);

		//You can add Your own capabilities here
		
		return result;
	}

	public HandlersProvider getHandlersProvider() {
		return handlersProvider;
	}

	public void setHandlersProvider(HandlersProvider handlersProvider) {
		this.handlersProvider = handlersProvider;
	}

	
	
}
