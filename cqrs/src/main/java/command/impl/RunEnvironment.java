/**
 * 
 */
package command.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import command.handler.CommandHandler;

/**
 * @author Slawek
 */
@Component
public class RunEnvironment {

	public interface HandlersProvider{
		CommandHandler<Object, Object> getHandler(Object command);
	}
	
	@Inject
	private HandlersProvider handlersProvider;
	
	public Object run(Object command) {		
		CommandHandler<Object, Object> handler = handlersProvider.getHandler(command);
		
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
