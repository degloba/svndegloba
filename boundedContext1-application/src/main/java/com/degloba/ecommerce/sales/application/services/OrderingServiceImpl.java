package com.degloba.ecommerce.sales.application.services;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.degloba.domain.annotations.ApplicationService;

import com.degloba.ecommerce.sales.application.commands.OrderDetailsCommand;
import com.degloba.ecommerce.sales.application.exceptions.OfferChangedException;
import com.degloba.ecommerce.sales.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.sales.domain.persistence.rdbms.jpa.ISalesRepository;
import com.degloba.ecommerce.sales.equivalent.SuggestionService;


import com.degloba.ecommerce.sales.offer.domain.factories.DiscountFactory;
import com.degloba.ecommerce.sales.offer.domain.persistence.rdbms.jpa.Offer;
import com.degloba.ecommerce.sales.offer.domain.policies.DiscountPolicy;
import com.degloba.ecommerce.sales.payment.domain.persistence.rdbms.jpa.Payment;
import com.degloba.ecommerce.sales.productscatalog.domain.persistence.rdbms.jpa.Product;
import com.degloba.ecommerce.sales.purchase.domain.factories.PurchaseFactory;

import com.degloba.ecommerce.sales.purchase.domain.persistence.rdbms.jpa.Purchase;
import com.degloba.ecommerce.sales.reservation.domain.factories.ReservationFactory;
import com.degloba.ecommerce.sales.reservation.domain.persistence.rdbms.jpa.Reservation;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.sharedkernel.exceptions.DomainOperationException;



/**
 * Ordering Use Case steps<br>
 * Each step is a Domain Story<br>
 * <br>
 * Notice that application language is different (simpler) than domain language, ex: we don'nt want to exposure domain concepts like Purchase and Reservation to the upper layers, we hide them under the Order term  
 * <br>
 * Technically App Service is just a bunch of procedures, therefore OO principles (ex: CqS, SOLID, GRASP) does not apply here  
 *
 * @author degloba
 */
@ApplicationService
public class OrderingServiceImpl implements IOrderingService {

/*	@Inject
	private SystemUser systemUser;*/
	
	@Inject
	private ISalesRepository salesRepository;

	@Inject
	private ReservationFactory reservationFactory;

	@Inject
	private PurchaseFactory purchaseFactory;
	
	@Inject
	private ISalesRepository productRepository;
	
	@Inject
	private DiscountFactory discountFactory;
	
	@Inject
	private SuggestionService suggestionService;

	// @Secured requires BUYER role
	public AggregateId createOrder() {
		Reservation reservation = reservationFactory.create(loadClient());
		salesRepository.save(reservation);
		return reservation.getAggregateId();
	}

	/**
	 * DOMAIN STORY<br>
	 * try to read this as a full sentence, this way: subject.predicate(completion)<br>
	 * <br>
	 * Load reservation by orderId<br>
	 * Load product by productId<br>
	 * Check if product is not available<br>
	 * -if so, than suggest equivalent for that product based on client<br>
	 * Reservation add product by given quantity
	 */
	@Override
	public void addProduct(AggregateId orderId, AggregateId productId,
			int quantity) {
		Reservation reservation = salesRepository.loadReservation(Reservation.class,orderId);
		
		Product product = productRepository.loadProduct(Product.class,productId);
		
		if (! product.isAvailabe()){
			Client client = loadClient();	
			product = suggestionService.suggestEquivalent(product, client);
		}
			
		reservation.add(product, quantity);
		
		salesRepository.save(reservation);
	}
	
	/**
	 * Can be invoked many times for the same order (with different params).<br>
	 * Offer VO is not stored in the Repo, it is stored on the Client Tier instead.
	 */
	public Offer calculateOffer(AggregateId orderId) {
		Reservation reservation = salesRepository.loadReservation(Reservation.class,orderId);

		DiscountPolicy discountPolicy = discountFactory.create(loadClient());
		
		/*
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 */
		return reservation.calculateOffer(discountPolicy);
	}

	/**
	 * DOMAIN STORY<br>
	 * try to read this as a full sentence, this way: subject.predicate(completion)<br>
	 * <br>
	 * Load reservation by orderId<br>
	 * Check if reservation is closed - if so, than Error<br>
	 * Generate new offer from reservation using discount created per client<br>
	 * Check if new offer is not the same as seen offer using delta = 5<br>
	 * Create purchase per client based on seen offer<br>
	 * Check if client can not afford total cost of purchase - if so, than Error<br>
	 * Confirm purchase<br>
	 * Close reservation<br>
	 */
	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)//highest isolation needed because of manipulating many Aggregates
	public void confirm(AggregateId orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedException {
		Reservation reservation = salesRepository.loadReservation(Reservation.class,orderId);
		if (reservation.isClosed())
			throw new DomainOperationException(reservation.getAggregateId(), "reservation is already closed");
		
		/*
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 */
		Offer newOffer = reservation.calculateOffer(
									discountFactory.create(loadClient()));
		
		/*
		 * Sample pattern: Client Tier sends back old VOs, Server generates new VOs based on Aggregate state<br>
		 * Notice that this VO is not stored in Repo, it's stored on the Client Tier. 
		 */
		if (! newOffer.sameAs(seenOffer, 5))//TODO load delta from conf.
			throw new OfferChangedException(reservation.getAggregateId(), seenOffer, newOffer);
		
		Client client = loadClient();//create per logged client, not reservation owner					
		Purchase purchase = purchaseFactory.create(reservation.getAggregateId(), client, seenOffer);
				
		if (! client.canAfford(purchase.getTotalCost()))
			throw new DomainOperationException(client.getAggregateId(), "client has insufficent money");
		
		salesRepository.save(purchase);//Aggregate must be managed by persistence context before firing events (synchronous listeners may need to load it) 
		
		/*
		 * Sample model where one aggregate creates another. Client does not manage payment lifecycle, therefore application must manage it. 
		 */
		Payment payment = client.charge(purchase.getTotalCost());
		salesRepository.save(payment);
		
		purchase.confirm();	
		reservation.close();				
		
		salesRepository.save(reservation);
		salesRepository.save(client);
		
	}
	
	private Client loadClient() {
		return null;
		////////////////return entityRepository.load(systemUser.getDomainUserId());
	}
}
