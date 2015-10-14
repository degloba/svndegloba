package com.degloba.ecommerce.sales.api.handler;

import javax.inject.Inject;

import command.annotations.CommandHandlerAnnotation;
import command.handler.CommandHandler;

import com.degloba.domain.EntityRepository;
import com.degloba.ecommerce.sales.api.command.AddProdctCommand;
import com.degloba.ecommerce.sales.client.Client;
import com.degloba.ecommerce.sales.client.IClientRepository;
import com.degloba.ecommerce.sales.equivalent.SuggestionService;
import com.degloba.ecommerce.sales.productscatalog.IProductRepository;
import com.degloba.ecommerce.sales.productscatalog.Product;
import com.degloba.ecommerce.sales.reservation.IReservationRepository;
import com.degloba.ecommerce.sales.reservation.Reservation;


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
