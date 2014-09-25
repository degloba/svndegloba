package com.degloba.boundedContext.application.api.handlers;

import javax.inject.Inject;

import com.degloba.boundedContext.application.api.commands.AddModalpanelCommand;
import com.degloba.boundedContext.domain.Client;
import com.degloba.boundedContext.domain.IClientRepository;
import com.degloba.boundedContext.domain.Modalpanel;
import com.degloba.boundedContext.domain.IModalpanelRepository;
import com.degloba.system.application.SystemContext;

// CQRS
import command.annotations.CommandHandlerAnnotation;
import command.handler.ICommandHandler;

// DDD
import domain.canonicalmodel.publishedlanguage.AggregateId;

@CommandHandlerAnnotation
public class AddModalpanelCommandHandler<K> implements ICommandHandler<AddModalpanelCommand<K>>{ 

	/*@Inject
	private ReservationRepository reservationRepository;
*/
	@Inject
	private IModalpanelRepository<AggregateId> modalpanelRepository;

	/*@Inject
	private SuggestionService suggestionService;
*/
	@Inject
	private IClientRepository<Long> clientRepository;

	@Inject
	private SystemContext systemContext;

	@Override
	public Void handle(AddModalpanelCommand<K> command) {
		//Reservation reservation = reservationRepository.load(command.getOrderId());

		Modalpanel modalPanel = modalpanelRepository.load(command.getModalpanelId());

		/*if (! modalPanel.isAvailabe()){
			Client client = loadClient();	
			modalPanel = suggestionService.suggestEquivalent(modalPanel, client);
		}*/

	/*	reservation.add(modalPanel, command.getQuantity());

		reservationRepository.save(reservation);*/

		return null;
	}

/*	private Client loadClient() {
		return clientRepository.load(systemContext.getSystemUser().getClientId());
	}*/
}
