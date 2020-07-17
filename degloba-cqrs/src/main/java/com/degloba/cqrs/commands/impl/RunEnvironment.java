package com.degloba.cqrs.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.degloba.cqrs.command.ICommand;
import com.degloba.cqrs.command.handler.ICommandHandler;

import lombok.Data;

/**
 * @author degloba
 * 
 * @category Component Spring que va executant {@link ICommand}
 * 
 * @param <K>
 */
@Component
@Data
public class RunEnvironment {

	/**
	 * @category Interficie : Proveidor de {@link ICommandHandler} a partir d'un {@link ICommand}
	 * 
	 */
	public interface IHandlersProvider{
		ICommandHandler<Object,Object> getHandler(Object command);
	}
	
	@Autowired
	private IHandlersProvider handlersProvider;
	
	public Object run(Object command) {		
		ICommandHandler<Object,Object> handler = handlersProvider.getHandler(command);
		
		//You can add Your own capabilities here: dependency injection, security, transaction management, logging, profiling, spying, storing commands, etc
		
		Object result = handler.handle(command);

		//You can add Your own capabilities here
		
		return result;
	}
	
}
