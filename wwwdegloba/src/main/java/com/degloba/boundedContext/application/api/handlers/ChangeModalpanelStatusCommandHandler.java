package com.degloba.boundedContext.application.api.handlers;

import javax.inject.Inject;

import com.degloba.boundedContext.application.api.commands.ChangeModalpanelStatusCommand;
import com.degloba.boundedContext.domain.Modalpanel;
import com.degloba.boundedContext.domain.IModalpanelRepository;

// CQRS
import command.annotations.CommandHandlerAnnotation;
import command.handler.ICommandHandler;


/**
 * @author degloba
 *
 */
@CommandHandlerAnnotation
public class ChangeModalpanelStatusCommandHandler implements ICommandHandler<ChangeModalpanelStatusCommand>{

	@Inject
	private IModalpanelRepository modalpanelRepository; 

	@Override
	public Void handle(ChangeModalpanelStatusCommand command) {
		Modalpanel modalpanel = modalpanelRepository.load(command.getModalpanelId());
		modalpanel.changeStatus(command.getStatus());
		modalpanelRepository.save(modalpanel);		
		return null;
	}

}