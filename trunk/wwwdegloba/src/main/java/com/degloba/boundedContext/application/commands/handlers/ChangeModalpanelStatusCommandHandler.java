package com.degloba.boundedContext.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.boundedContext.application.commands.ChangeModalpanelStatusCommand;
import com.degloba.boundedContext.domain.Modalpanel;
import com.degloba.boundedContext.domain.ModalpanelRepository;

import command.annotations.CommandHandlerAnnotation;
import command.handler.CommandHandler;

/**
 * @author Slawek
 *
 */
@CommandHandlerAnnotation
public class ChangeModalpanelStatusCommandHandler implements CommandHandler<ChangeModalpanelStatusCommand, Void>{

	@Inject
	private ModalpanelRepository modalpanelRepository; 

	@Override
	public Void handle(ChangeModalpanelStatusCommand command) {
		Modalpanel modalpanel = modalpanelRepository.load(command.getCustomerId());
		modalpanel.changeStatus(command.getStatus());
		modalpanelRepository.save(modalpanel);		
		return null;
	}

}