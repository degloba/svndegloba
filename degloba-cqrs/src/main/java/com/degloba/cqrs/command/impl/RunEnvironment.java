package com.degloba.cqrs.command.impl;

import javax.inject.Inject;

// Spring
import org.springframework.stereotype.Component;

// CQRS
import com.degloba.cqrs.command.handler.ICommandHandler;

/**
 * @author degloba
 * @param <K>
 */
@Component
public class RunEnvironment {

	public interface IHandlersProvider{
		ICommandHandler<Object> getHandler(Object command);
	}
	
	@Inject
	private IHandlersProvider handlersProvider;
	
	public Object run(Object command) {		
		ICommandHandler<Object> handler = handlersProvider.getHandler(command);
		
		//You can add Your own capabilities here: dependency injection, security, transaction management, logging, profiling, spying, storing commands, etc
		
		Object result = handler.handle(command);

		//You can add Your own capabilities here
		
		return result;
	}

	public IHandlersProvider getHandlersProvider() {
		return handlersProvider;
	}

	public void setHandlersProvider(IHandlersProvider handlersProvider) {
		this.handlersProvider = handlersProvider;
	}

	
	
}
