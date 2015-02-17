package com.degloba.modalpanel.application.api.handlers;

import javax.inject.Inject;




import com.degloba.casino.modalpanel.IModalpanelRepository;
import com.degloba.casino.modalpanel.Modalpanel;



// CQRS
import command.annotations.CommandHandlerAnnotation;
import command.handler.ICommandHandler;

import com.degloba.domain.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.modalpanel.application.api.commands.ChangeModalpanelStatusCommand;


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