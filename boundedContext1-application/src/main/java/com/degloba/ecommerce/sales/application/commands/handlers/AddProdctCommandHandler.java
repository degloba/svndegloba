package com.degloba.ecommerce.sales.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;

// Application
import com.degloba.ecommerce.sales.application.commands.AddProdctCommand;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendaRepository;
import com.degloba.ecommerce.vendes.equivalent.SuggestionService;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reservation;


///////////////import com.degloba.ecommerce.system.SystemUser;

@CommandHandlerAnnotation
public class AddProdctCommandHandler implements ICommandHandler<AddProdctCommand, Void>{


	@Inject
	private IVendaRepository vendaRepository; 

	
	@Inject
	private SuggestionService suggestionService;
	
	
/*	@Inject
	private SystemUser systemUser;*/
	
	@Override
	public Void handle(AddProdctCommand command) {
		Reservation reservation = vendaRepository.loadReservation(Reservation.class,command.getOrderId());
		
		Producte producte = vendaRepository.loadProduct(Producte.class,command.getProductId());
		
		if (! producte.isAvailabe()){
			Client client = loadClient();	
			producte = suggestionService.suggestEquivalent(producte, client);
		}
			
		reservation.add(producte, command.getQuantity());
		
		vendaRepository.save(reservation);
		
		return null;
	}
	
	private Client loadClient() {
		return null;
///////////////return entityRepository.load(Client.class,systemUser.getDomainUserId());
	}

}
