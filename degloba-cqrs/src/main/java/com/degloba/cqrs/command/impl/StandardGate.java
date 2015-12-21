package com.degloba.cqrs.command.impl;

import javax.inject.Inject;

// Spring
import org.springframework.stereotype.Component;

// CQRS
import com.degloba.cqrs.command.Gate;
import com.degloba.cqrs.command.annotations.Command;


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
			//TODO add to the queue. Queue should send this command to the RunEnvironment
			return null;
		}
		
		
		return runEnvironment.run(command);
	}

	/**
	 * @param command
	 * @return
	 */
	private boolean isAsynchronous(Object command) {
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
