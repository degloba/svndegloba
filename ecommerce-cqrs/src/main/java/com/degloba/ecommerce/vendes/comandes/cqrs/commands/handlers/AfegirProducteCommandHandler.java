package com.degloba.ecommerce.vendes.comandes.cqrs.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandler;
import com.degloba.cqrs.command.handler.ICommandHandler;
import com.degloba.ecommerce.vendes.comandes.cqrs.commands.AfegirProducteCommand;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendaRepository;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.domain.services.SuggerimentService;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;


///////////////import com.degloba.ecommerce.system.SystemUser;

@CommandHandler
public class AfegirProducteCommandHandler implements ICommandHandler<AfegirProducteCommand, Void>{


	@Inject
	private IVendaRepository vendaRepository; 

	
	@Inject
	private SuggerimentService suggerimentService;
	
	
/*	@Inject
	private SystemUser systemUser;*/
	
	@Override
	public Void handle(AfegirProducteCommand command) {
		Reserva reserva = vendaRepository.obtenirReservaById(Reserva.class,command.getComandaId());
		
		Producte producte = vendaRepository.obtenirProducteById(Producte.class,command.getProducteId());
		
		if (! producte.isAvailabe()){
			Client client = loadClient();	
			producte = suggerimentService.suggerirProducteEquivalent(producte, client);
		}
			
		reserva.add(producte, command.getQuantitat());
		
		vendaRepository.save(reserva);
		
		return null;
	}
	
	private Client loadClient() {
		return null;
///////////////return entityRepository.load(Client.class,systemUser.getDomainUserId());
	}

}
