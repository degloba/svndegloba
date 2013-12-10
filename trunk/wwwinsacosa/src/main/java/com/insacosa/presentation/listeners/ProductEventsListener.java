/**
 * 
 */
package com.insacosa.presentation.listeners;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import infrastructure.events.EventListener;
import infrastructure.events.EventListeners;
import com.insacosa.application.events.ProductAddedToOrderEvent;
import com.insacosa.presentation.model.OrderedProduct;

/**
 * Sample of updating presentation model when event occurs
 * 
 * @author Slawek
 *
 */
@EventListeners
public class ProductEventsListener {

	@PersistenceContext
	private EntityManager entityManager;
	
	@EventListener(asynchronous=true)
	public void handle(ProductAddedToOrderEvent event){
		entityManager.persist(new OrderedProduct(event.getProductid(), event.getClientId(), event.getQuantity(), new Date()));
	}
}
