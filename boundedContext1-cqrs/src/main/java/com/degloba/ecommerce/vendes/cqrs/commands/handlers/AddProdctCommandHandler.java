package com.degloba.ecommerce.vendes.cqrs.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.cqrs.commands.AfegirProducteCommand;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendesRepository;
import com.degloba.ecommerce.vendes.equivalent.SuggestionService;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;


///////////////import com.degloba.ecommerce.system.SystemUser;

@CommandHandlerAnnotation
public class AddProdctCommandHandler implements ICommandHandler<AfegirProducteCommand, Void>{


	@Inject
	private IVendesRepository vendesRepository; 

	
	@Inject
	private SuggestionService suggestionService;
	
	
/*	@Inject
	private SystemUser systemUser;*/
	
	@Override
	public Void handle(AfegirProducteCommand command) {
		Reserva reserva = vendesRepository.carregaReserva(Reserva.class,command.getComandaId());
		
		Producte producte = vendesRepository.carregaProducte(Producte.class,command.getProducteId());
		
		if (! producte.isAvailabe()){
			Client client = loadClient();	
			producte = suggestionService.suggestEquivalent(producte, client);
		}
			
		reserva.add(producte, command.getQuantitat());
		
		vendesRepository.save(reserva);
		
		return null;
	}
	
	private Client loadClient() {
		return null;
///////////////return entityRepository.load(Client.class,systemUser.getDomainUserId());
	}

}
