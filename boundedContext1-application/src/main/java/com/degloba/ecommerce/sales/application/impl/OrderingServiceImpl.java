package com.degloba.ecommerce.sales.application.impl;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.degloba.domain.annotations.ApplicationService;
import com.google.appengine.api.datastore.Key;

//import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.ecommerce.sales.application.api.command.OrderDetailsCommand;
import com.degloba.ecommerce.sales.application.api.service.OfferChangedExcpetion;
import com.degloba.ecommerce.sales.application.api.service.OrderingService;
import com.degloba.ecommerce.sales.client.domain.Client;
import com.degloba.ecommerce.sales.client.domain.IClientRepository;
//import pl.com.bottega.ecommerce.sales.domain.client.ClientRepository;
import com.degloba.ecommerce.sales.equivalent.SuggestionService;
import com.degloba.ecommerce.sales.offer.domain.DiscountFactory;
import com.degloba.ecommerce.sales.offer.domain.DiscountPolicy;
import com.degloba.ecommerce.sales.offer.domain.Offer;
import com.degloba.ecommerce.sales.payment.domain.IPaymentRepository;
import com.degloba.ecommerce.sales.payment.domain.Payment;
import com.degloba.ecommerce.sales.productscatalog.domain.IProductRepository;
//import pl.com.bottega.ecommerce.sales.domain.payment.PaymentRepository;
import com.degloba.ecommerce.sales.productscatalog.domain.Product;
import com.degloba.ecommerce.sales.purchase.domain.IPurchaseRepository;
//import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductRepository;
import com.degloba.ecommerce.sales.purchase.domain.Purchase;
import com.degloba.ecommerce.sales.purchase.domain.PurchaseFactory;
import com.degloba.ecommerce.sales.reservation.domain.IReservationRepository;
//import pl.com.bottega.ecommerce.sales.domain.purchase.PurchaseRepository;
import com.degloba.ecommerce.sales.reservation.domain.Reservation;
import com.degloba.ecommerce.sales.reservation.domain.ReservationFactory;
import com.degloba.domain.EntityRepository;
//import pl.com.bottega.ecommerce.sales.domain.reservation.ReservationRepository;
import com.degloba.domain.sharedkernel.exceptions.DomainOperationException;

import com.degloba.ecommerce.system.application.SystemUser;

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
public class OrderingServiceImpl implements OrderingService {

/*	@Inject
	private SystemUser systemUser;*/
	
	@Inject
	private EntityRepository entityRepository;
	
	@Inject
	private IClientRepository clientRepository;

	@Inject
	private IReservationRepository reservationRepository;

	@Inject
	private ReservationFactory reservationFactory;

	@Inject
	private PurchaseFactory purchaseFactory;

	@Inject
	private IPurchaseRepository purchaseRepository;
	
	@Inject
	private IProductRepository productRepository;
	
	@Inject 
	private IPaymentRepository paymentRepository;

	@Inject
	private DiscountFactory discountFactory;
	
	@Inject
	private SuggestionService suggestionService;

	// @Secured requires BUYER role
	public Key createOrder() {
		Reservation reservation = reservationFactory.create(loadClient());
		entityRepository.save(reservation);
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
	public void addProduct(Key orderId, Key productId,
			int quantity) {
		Reservation reservation = reservationRepository.load(Reservation.class,orderId);
		
		Product product = productRepository.load(Product.class,productId);
		
		if (! product.isAvailabe()){
			Client client = loadClient();	
			product = suggestionService.suggestEquivalent(product, client);
		}
			
		reservation.add(product, quantity);
		
		entityRepository.save(reservation);
	}
	
	/**
	 * Can be invoked many times for the same order (with different params).<br>
	 * Offer VO is not stored in the Repo, it is stored on the Client Tier instead.
	 */
	public Offer calculateOffer(Key orderId) {
		Reservation reservation = entityRepository.load(Reservation.class,orderId);

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
	public void confirm(Key orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedExcpetion {
		Reservation reservation = entityRepository.load(Reservation.class,orderId);
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
			throw new OfferChangedExcpetion(reservation.getAggregateId(), seenOffer, newOffer);
		
		Client client = loadClient();//create per logged client, not reservation owner					
		Purchase purchase = purchaseFactory.create(reservation.getAggregateId(), client, seenOffer);
				
		if (! client.canAfford(purchase.getTotalCost()))
			throw new DomainOperationException(client.getAggregateId(), "client has insufficent money");
		
		entityRepository.save(purchase);//Aggregate must be managed by persistence context before firing events (synchronous listeners may need to load it) 
		
		/*
		 * Sample model where one aggregate creates another. Client does not manage payment lifecycle, therefore application must manage it. 
		 */
		Payment payment = client.charge(purchase.getTotalCost());
		entityRepository.save(payment);
		
		purchase.confirm();	
		reservation.close();				
		
		entityRepository.save(reservation);
		entityRepository.save(client);
		
	}
	
	private Client loadClient() {
		return null;
		////////////////return entityRepository.load(systemUser.getDomainUserId());
	}
}
