package com.degloba.cqrs.command.impl;

import javax.inject.Inject;

// Spring
import org.springframework.stereotype.Component;

// CQRS
import com.degloba.cqrs.command.Gate;
import com.degloba.cqrs.command.annotations.Command;

/**
 * 
 * @author degloba
 * 
 * @category Runtime del CQRS. Té definit :
 * <ul>
 * <li>
 * un històric de {@link Command} executades
 * </li>
 * <li>
 * el Runtime CQRS propiament dit
 * </li>
 * </ul>
 *
 */
@Component
public class StandardGate implements Gate {
	
	@Inject
	private RunEnvironment runEnvironment;
	
	private GateHistory gateHistory = new GateHistory();

	/* (non-Javadoc)
	 * @see command.impl.Gate#dispatch(java.lang.Object)
	 */
	public Object dispatch(Object command){
		if (! gateHistory.register(command)){
			//TODO log.info(duplicate)
			return null;//skip duplicate
		}
			
		if (isAsynchronous(command)){
			//TODO afegir a la cua. La cua ha d’enviar aquesta {@link Command} al RunEnvironment
			return null;
		}
		
		
		return runEnvironment.run(command);
	}

	/**
	 * @category Retorna si el {@link Command} es assícron o no
	 * En el cas assíncron hauríem de posar el {@link Command} en una cua 
	 * 
	 * @param command
	 * @return
	 */
	private boolean isAsynchronous(Object command) {
		// comprovem que el {@link Object} es un {@link Commnad}
		if (! command.getClass().isAnnotationPresent(Command.class))
			return false;
		
		Command commandAnnotation = command.getClass().getAnnotation(Command.class);		
		return commandAnnotation.asynchronous();		
	}

	public RunEnvironment getRunEnvironment() {
		return runEnvironment;
	}

	public void setRunEnvironment(RunEnvironment runEnvironment) {
		this.runEnvironment = runEnvironment;
	}

	
}
