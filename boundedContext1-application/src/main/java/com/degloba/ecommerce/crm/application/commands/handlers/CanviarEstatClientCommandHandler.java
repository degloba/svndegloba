package com.degloba.ecommerce.crm.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;

import com.degloba.ecommerce.crm.application.commands.CanviarEstatClientCommand;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.crm.domain.persistence.rdbms.jpa.ICrmRepository;


/**
 * @author degloba
 * 
 * @category Modifica l'estat d'un {@link Client} a partir del {@link Command} {@link CanviarEstatClientCommand}
 *
 */
@CommandHandlerAnnotation
public class CanviarEstatClientCommandHandler implements ICommandHandler<CanviarEstatClientCommand,Boolean>{

	@Inject
	private ICrmRepository crmRepository; 
	
	@Override
	public Boolean handle(CanviarEstatClientCommand command) {
		Client client = crmRepository.get(Client.class, command.getClientId());
		client.canviaEstatClient(command.getEstatClient());
		crmRepository.save(client);		
		return null;
	}

}
