package com.degloba.ecommerce.sales.application.api.handler;

import javax.inject.Inject;

import com.degloba.cqrs.command.annotations.CommandHandlerAnnotation;
import com.degloba.cqrs.command.handler.CommandHandler;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;
// Application
import com.degloba.ecommerce.sales.application.api.command.AddProdctCommand;

// Domain
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa.ISalesRepository;
import com.degloba.ecommerce.sales.equivalent.SuggestionService;
import com.degloba.ecommerce.sales.productscatalog.domain.IProductRepository;
import com.degloba.ecommerce.sales.productscatalog.domain.Product;
import com.degloba.ecommerce.sales.reservation.domain.IReservationRepository;
import com.degloba.ecommerce.sales.reservation.domain.Reservation;


///////////////import com.degloba.ecommerce.system.SystemUser;

@CommandHandlerAnnotation
public class AddProdctCommandHandler implements CommandHandler<AddProdctCommand, Void>{

	@Inject
	private IEntityRepository entityRepository;
	
	@Inject
	private IReservationRepository reservationRepository;   
	
	@Inject
	private IProductRepository productRepository; 
	
	@Inject
	private ISalesRepository salesRepository;
	
	
	@Inject
	private SuggestionService suggestionService;
	
	
/*	@Inject
	private SystemUser systemUser;*/
	
	@Override
	public Void handle(AddProdctCommand command) {
		Reservation reservation = entityRepository.load(Reservation.class,command.getOrderId());
		
		Product product = entityRepository.load(Product.class,command.getProductId());
		
		if (! product.isAvailabe()){
			Client client = loadClient();	
			product = suggestionService.suggestEquivalent(product, client);
		}
			
		reservation.add(product, command.getQuantity());
		
		entityRepository.save(reservation);
		
		return null;
	}
	
	private Client loadClient() {
		return null;
///////////////return entityRepository.load(Client.class,systemUser.getDomainUserId());
	}

}
