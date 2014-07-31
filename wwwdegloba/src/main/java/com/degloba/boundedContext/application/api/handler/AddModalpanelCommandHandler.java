package com.degloba.boundedContext.application.api.handler;

import javax.inject.Inject;

import com.degloba.boundedContext.application.api.command.AddModalpanelCommand;
import com.degloba.boundedContext.domain.Client;
import com.degloba.boundedContext.domain.ClientRepository;
import com.degloba.boundedContext.domain.Modalpanel;
import com.degloba.boundedContext.domain.ModalpanelRepository;
import com.degloba.system.application.SystemContext;

import command.annotations.CommandHandlerAnnotation;
import command.handler.CommandHandler;

@CommandHandlerAnnotation
public class AddModalpanelCommandHandler implements CommandHandler<AddModalpanelCommand, Void>{ 

	/*@Inject
	private ReservationRepository reservationRepository;
*/
	@Inject
	private ModalpanelRepository modalpanelRepository;

	/*@Inject
	private SuggestionService suggestionService;
*/
	@Inject
	private ClientRepository clientRepository;

	@Inject
	private SystemContext systemContext;

	@Override
	public Void handle(AddModalpanelCommand command) {
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

	private Client loadClient() {
		return clientRepository.load(systemContext.getSystemUser().getClientId());
	}
}
