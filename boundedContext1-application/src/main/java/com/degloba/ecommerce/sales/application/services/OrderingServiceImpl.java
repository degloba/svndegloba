package com.degloba.ecommerce.sales.application.services;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.degloba.domain.annotations.ApplicationService;

import com.degloba.ecommerce.sales.application.commands.OrderDetailsCommand;
import com.degloba.ecommerce.sales.application.exceptions.OfferChangedException;
import com.degloba.ecommerce.vendes.catalegProductes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ecommerce.vendes.compres.domain.factories.PurchaseFactory;
import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendaRepository;
import com.degloba.ecommerce.vendes.equivalent.SuggestionService;
import com.degloba.ecommerce.vendes.ofertes.domain.factories.DescompteFactory;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
import com.degloba.ecommerce.vendes.ofertes.domain.policies.DescomptePolicy;
import com.degloba.ecommerce.vendes.pagaments.domain.persistence.rdbms.jpa.Payment;
import com.degloba.ecommerce.vendes.reserves.domain.factories.ReservationFactory;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reservation;
import com.degloba.persistence.domain.AggregateId;
import com.degloba.persistence.domain.sharedkernel.exceptions.DomainOperationException;



/**
 * Ordering Use Case steps<br>
 * Each step is a Domain Story<br>
 * <br>
 * Cal tenir en compte que el llenguatge de l’aplicació és diferent (més simple) que el llenguatge del domini,</br>
 *  per exemple: no volem exposar conceptes de domini com ara la {@link Compra} i la {@link Reservation} a les capes superiors, 
 * que les amagem sota el terme {@link Order}.  
 * <br>
 * Tècnicament, el servei d’aplicacions és només un munt de procediments; per tant, els principis d’OO (ex: CqS, SOLID, GRASP) no s’apliquen aquí
 *
 * @author degloba
 */
@ApplicationService
public class OrderingServiceImpl implements IOrderingService {

/*	@Inject
	private SystemUser systemUser;*/
	
	@Inject
	private IVendaRepository vendaRepository;

	@Inject
	private ReservationFactory reservationFactory;

	@Inject
	private PurchaseFactory purchaseFactory;
	
	@Inject
	private IVendaRepository productRepository;
	
	@Inject
	private DescompteFactory descompteFactory;
	
	@Inject
	private SuggestionService suggestionService;

	// @Secured requires BUYER role
	public AggregateId createOrder() {
		Reservation reservation = reservationFactory.create(loadClient());
		vendaRepository.save(reservation);
		return reservation.getAggregateId();
	}

	/**
	 * DOMAIN STORY<br>
	 * try to read this as a full sentence, this way: subject.predicate(completion)<br>
	 * <br>
	 * Carga la {@link Reservation} per la orderId<br>
	 * Carga el {@link Producte} pel productId<br>
	 * Comprova si el {@link Producte} no és accessible<br>
	 * si es així, llavors s suggereix un equivalent d'aquest {@link Producte} basat en el client<br>
	 * Reservation add product by given quantity
	 */
	@Override
	public void addProduct(AggregateId orderId, AggregateId productId,
			int quantity) {
		Reservation reservation = vendaRepository.loadReservation(Reservation.class,orderId);
		
		Producte producte = productRepository.loadProduct(Producte.class,productId);
		
		if (! producte.isAvailabe()){
			Client client = loadClient();	
			producte = suggestionService.suggestEquivalent(producte, client);
		}
			
		reservation.add(producte, quantity);
		
		vendaRepository.save(reservation);
	}
	
	/**
	 * Can be invoked many times for the same order (with different params).<br>
	 * Offer VO is not stored in the Repo, it is stored on the Client Tier instead.
	 */
	public Oferta calculateOffer(AggregateId orderId) {
		Reservation reservation = vendaRepository.loadReservation(Reservation.class,orderId);

		DescomptePolicy descomptePolicy = descompteFactory.create(loadClient());
		
		/*
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 */
		return reservation.calculateOffer(descomptePolicy);
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
	public void confirm(AggregateId orderId, OrderDetailsCommand orderDetailsCommand, Oferta seenOffer)
			throws OfferChangedException {
		Reservation reservation = vendaRepository.loadReservation(Reservation.class,orderId);
		if (reservation.isClosed())
			throw new DomainOperationException(reservation.getAggregateId(), "reservation is already closed");
		
		/*
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 */
		Oferta newOffer = reservation.calculateOffer(
									descompteFactory.create(loadClient()));
		
		/*
		 * Sample pattern: Client Tier sends back old VOs, Server generates new VOs based on Aggregate state<br>
		 * Notice that this VO is not stored in Repo, it's stored on the Client Tier. 
		 */
		if (! newOffer.sameAs(seenOffer, 5))//TODO load delta from conf.
			throw new OfferChangedException(reservation.getAggregateId(), seenOffer, newOffer);
		
		Client client = loadClient();//create per logged client, not reservation owner					
		Compra compra = purchaseFactory.create(reservation.getAggregateId(), client, seenOffer);
				
		if (! client.canAfford(compra.getTotalCost()))
			throw new DomainOperationException(client.getAggregateId(), "client has insufficent money");
		
		vendaRepository.save(compra);//Aggregate must be managed by persistence context before firing events (synchronous listeners may need to load it) 
		
		/*
		 * Sample model where one aggregate creates another. Client does not manage payment lifecycle, therefore application must manage it. 
		 */
		Payment payment = client.charge(compra.getTotalCost());
		vendaRepository.save(payment);
		
		compra.confirm();	
		reservation.close();				
		
		vendaRepository.save(reservation);
		vendaRepository.save(client);
		
	}
	
	private Client loadClient() {
		return null;
		////////////////return entityRepository.load(systemUser.getDomainUserId());
	}
}
