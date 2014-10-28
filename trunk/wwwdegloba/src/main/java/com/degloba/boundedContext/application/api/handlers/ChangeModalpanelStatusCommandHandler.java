package com.degloba.boundedContext.application.api.handlers;

import javax.inject.Inject;

import com.degloba.boundedContext.application.api.commands.ChangeModalpanelStatusCommand;
import com.degloba.boundedContext.domain.modalpanel.Modalpanel;
import com.degloba.boundedContext.domain.modalpanel.IModalpanelRepository;

// CQRS
import command.annotations.CommandHandlerAnnotation;
import command.handler.ICommandHandler;


/**
 * @author degloba
 *
 */
@CommandHandlerAnnotation
public class ChangeModalpanelStatusCommandHandler<K> implements ICommandHandler<ChangeModalpanelStatusCommand>{

	@Inject
	private IModalpanelRepository<Long> modalpanelRepository; 

	@Override
	public Void handle(ChangeModalpanelStatusCommand command) {
		Modalpanel modalpanel = modalpanelRepository.load(command.getModalpanelId());
		modalpanel.changeStatus(command.getStatus());
		modalpanelRepository.save(modalpanel);		
		return null;
	}

}