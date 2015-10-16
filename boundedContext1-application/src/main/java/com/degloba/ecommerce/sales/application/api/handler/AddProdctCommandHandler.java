package com.degloba.ecommerce.sales.application.api.handler;

import javax.inject.Inject;

import command.annotations.CommandHandlerAnnotation;
import command.handler.CommandHandler;

import com.degloba.domain.EntityRepository;
import com.degloba.ecommerce.sales.application.api.command.AddProdctCommand;
import com.degloba.ecommerce.sales.client.domain.Client;
import com.degloba.ecommerce.sales.client.domain.IClientRepository;
import com.degloba.ecommerce.sales.equivalent.SuggestionService;
import com.degloba.ecommerce.sales.productscatalog.domain.IProductRepository;
import com.degloba.ecommerce.sales.productscatalog.domain.Product;
import com.degloba.ecommerce.sales.reservation.domain.IReservationRepository;
import com.degloba.ecommerce.sales.reservation.domain.Reservation;


//import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
//import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
//import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
///////////////import com.degloba.ecommerce.system.SystemUser;

@CommandHandlerAnnotation
public class AddProdctCommandHandler implements CommandHandler<AddProdctCommand, Void>{

	@Inject
	private EntityRepository entityRepository;
	
	@Inject
	private IReservationRepository reservationRepository;   
	
	@Inject
	private IProductRepository productRepository; 
	
	@Inject
	private IClientRepository clientRepository;
	
	
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
