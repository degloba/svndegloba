/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.degloba.ecommerce.sales.api.handler;

import javax.inject.Inject;

import command.annotations.CommandHandlerAnnotation;
import command.handler.CommandHandler;

import com.degloba.domain.EntityRepository;
import com.degloba.ecommerce.sales.api.command.AddProdctCommand;
import com.degloba.ecommerce.sales.client.Client;
//import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import com.degloba.ecommerce.sales.equivalent.SuggestionService;
import com.degloba.ecommerce.sales.productscatalog.Product;
//import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import com.degloba.ecommerce.sales.reservation.Reservation;
//import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
///////////////import com.degloba.ecommerce.system.SystemUser;

@CommandHandlerAnnotation
public class AddProdctCommandHandler implements CommandHandler<AddProdctCommand, Void>{

	@Inject
	private EntityRepository entityRepository;
	
/*	@Inject
	private ReservationRepository reservationRepository;
	
	@Inject
	private ProductRepository productRepository;
	
	@Inject
	private ClientRepository clientRepository;*/
	
	
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
