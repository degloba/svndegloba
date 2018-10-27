package com.degloba.ecommerce.sales.application.commands.handlers;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.ICommandHandler;

// Application
import com.degloba.ecommerce.sales.application.commands.AddProdctCommand;
// Domain
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa.ISalesRepository;
import com.degloba.ecommerce.sales.equivalent.SuggestionService;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;
import com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa.Reservation;


///////////////import com.degloba.ecommerce.system.SystemUser;

@CommandHandlerAnnotation
public class AddProdctCommandHandler implements ICommandHandler<AddProdctCommand, Void>{


	@Inject
	private ISalesRepository salesRepository; 

	
	@Inject
	private SuggestionService suggestionService;
	
	
/*	@Inject
	private SystemUser systemUser;*/
	
	@Override
	public Void handle(AddProdctCommand command) {
		Reservation reservation = salesRepository.loadReservation(Reservation.class,command.getOrderId());
		
		Product product = salesRepository.loadProduct(Product.class,command.getProductId());
		
		if (! product.isAvailabe()){
			Client client = loadClient();	
			product = suggestionService.suggestEquivalent(product, client);
		}
			
		reservation.add(product, command.getQuantity());
		
		salesRepository.save(reservation);
		
		return null;
	}
	
	private Client loadClient() {
		return null;
///////////////return entityRepository.load(Client.class,systemUser.getDomainUserId());
	}

}
