package com.degloba.boundedContext.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.boundedContext.application.commands.ChangeModalpanelStatusCommand;
import com.degloba.boundedContext.domain.Modalpanel;
import com.degloba.boundedContext.domain.ModalpanelRepository;

// DDD
import command.annotations.CommandHandlerAnnotation;
import command.handler.CommandHandler;


/**
 * @author degloba
 *
 */
@CommandHandlerAnnotation
public class ChangeModalpanelStatusCommandHandler<K> implements CommandHandler<ChangeModalpanelStatusCommand<K>, Void>{

	@Inject
	private ModalpanelRepository<K> modalpanelRepository; 

	@Override
	public Void handle(ChangeModalpanelStatusCommand<K> command) {
		Modalpanel modalpanel = modalpanelRepository.load(command.getModalpanelId());
		modalpanel.changeStatus(command.getStatus());
		modalpanelRepository.save(modalpanel);		
		return null;
	}

}