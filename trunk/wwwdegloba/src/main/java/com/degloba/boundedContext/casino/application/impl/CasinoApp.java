package com.degloba.boundedContext.casino.application.impl;

import javax.inject.Inject;





// SPRING
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


//




import com.degloba.boundedContext.casino.application.api.service.ICasinoApp;
import com.degloba.boundedContext.casino.domain.ICasinoClasseService;
import com.degloba.boundedContext.casino.domain.IEntityService;
import com.degloba.boundedContext.clients.domain.Empresa;
import com.degloba.boundedContext.clients.domain.IEmpresaRepository;
import com.degloba.boundedContext.clients.domain.IEmpresaService;
import com.degloba.boundedContext.modalpanel.domain.IModalpanelRepository;
import com.degloba.boundedContext.modalpanel.domain.Modalpanel;
import com.degloba.boundedContext.modalpanel.domain.ModalpanelFactory;
import com.degloba.system.application.SystemContext;


// DDD
import domain.canonicalmodel.publishedlanguage.AggregateId;
import domain.sharedkernel.exceptions.DomainOperationException;
import domain.support.BaseEntity;
import application.BaseAggregateRootApplicationService;
import application.annotations.ApplicationService;

/**
 * Ordering Use Case steps<br>
 * Each step is a Domain Story<br>
 * <br>
 * Notice that application language is different (simpler) than domain language, 
 * ex: we don'nt want to exposure domain concepts like Purchase and Reservation to the upper layers, 
 * we hide them under the Order term  
 * Technically App Service is just a bunch of procedures, therefore OO principles (ex: CqS, SOLID, GRASP) 
 * does not apply here  
 *
 * @author degloba
 */
@ApplicationService
public class CasinoApp<K> extends BaseAggregateRootApplicationService<K,Modalpanel>  
							implements ICasinoApp<K> {

	// SERVEIS DE DOMINI
	@Inject
	private ICasinoClasseService _casinoClasseService;
	
	@Inject
	private IEmpresaService<Long> _empresaService;
	
	/*@Inject
	private SuggestionService suggestionService;*/
	
/*	@Inject
	private SystemContext systemContext; */

	// REPOSITORIES
/*	@Inject
	private IEmpresaRepository<Long> empresaRepository;*/

/*	@Inject
	private IModalpanelRepository<Long> modalpanelRepository;*/
	
	// FACTORIES
	@Inject
	private ModalpanelFactory modalpanelFactory;  


	// @Secured requires BUYER role
/*	public AggregateId createOrder() {
		Reservation reservation = reservationFactory.create(loadClient());
		reservationRepository.save(reservation);
		return reservation.getAggregateId();
	}*/

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
/*	@Override
	public void addProduct(AggregateId orderId, AggregateId productId,
			int quantity) {
		Reservation reservation = reservationRepository.load(orderId);

		Product product = productRepository.load(productId);

		if (! product.isAvailabe()){
			Client client = loadClient();	
			product = suggestionService.suggestEquivalent(product, client);
		}

		reservation.add(product, quantity);

		reservationRepository.save(reservation);
	}*/
	
	
/*	@Override
	public void addModalpanel(Long modalpanelId) {
		// TODO Auto-generated method stub
		
		//Modalpanel modalpanel = this.modalpanelRepository.load(modalpanelId);
		Modalpanel modalpanel = new Modalpanel();
		modalpanel.setDescripcio("nou");
		
		this.modalpanelRepository.save(modalpanel);
		
	}*/

	/**
	 * Can be invoked many times for the same order (with different params).<br>
	 * Offer VO is not stored in the Repo, it is stored on the Client Tier instead.
	 */
/*	public Offer calculateOffer(AggregateId orderId) {
		Reservation reservation = reservationRepository.load(orderId);

		DiscountPolicy discountPolicy = discountFactory.create(loadClient());

		
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 
		return reservation.calculateOffer(discountPolicy);
	}*/

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
/*	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)//highest isolation needed because of manipulating many Aggregates
	public void confirm(AggregateId orderId, OrderDetailsCommand orderDetailsCommand, Offer seenOffer)
			throws OfferChangedExcpetion {
		Reservation reservation = reservationRepository.load(orderId);
		if (reservation.isClosed())
			throw new DomainOperationException(reservation.getAggregateId(), "reservation is already closed");

		
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 
		Offer newOffer = reservation.calculateOffer(
									discountFactory.create(loadClient()));

		
		 * Sample pattern: Client Tier sends back old VOs, Server generates new VOs based on Aggregate state<br>
		 * Notice that this VO is not stored in Repo, it's stored on the Client Tier. 
		 
		if (! newOffer.sameAs(seenOffer, 5))//TODO load delta from conf.
			throw new OfferChangedExcpetion(reservation.getAggregateId(), seenOffer, newOffer);

		Client client = loadClient();//create per logged client, not reservation owner					
		Purchase purchase = purchaseFactory.create(reservation.getAggregateId(), client, seenOffer);

		if (! client.canAfford(purchase.getTotalCost()))
			throw new DomainOperationException(client.getAggregateId(), "client has insufficent money");

		purchaseRepository.save(purchase);//Aggregate must be managed by persistence context before firing events (synchronous listeners may need to load it) 

		
		 * Sample model where one aggregate creates another. Client does not manage payment lifecycle, therefore application must manage it. 
		 
		Payment payment = client.charge(purchase.getTotalCost());
		paymentRepository.save(payment);

		purchase.confirm();	
		reservation.close();				

		reservationRepository.save(reservation);
		clientRepository.save(client);

	}*/

/*	private Client loadClient() {
		return clientRepository.load(systemContext.getSystemUser().getClientId());
	}*/

	@Override
	public K createModalpanel() {
		// TODO Auto-generated method stub
		return null;
	}


/*	public IModalpanelRepository<Long> getModalpanelRepository() {
		return modalpanelRepository;
	}

	public void setModalpanelRepository(IModalpanelRepository<Long> modalpanelRepository) {
		this.modalpanelRepository = modalpanelRepository;
	}*/

	

/*	public IEmpresaRepository<Long> getEmpresaRepository() {
		return empresaRepository;
	}


	public void setEmpresaRepository(IEmpresaRepository<Long> empresaRepository) {
		this.empresaRepository = empresaRepository;
	}*/


	@Override
	public void addModalpanel(Modalpanel value) {
		// TODO Auto-generated method stub
		
		IEntityService<Modalpanel> service = this._casinoClasseService.createService();
		IEntityService<Empresa> service2 = this._casinoClasseService.createService();
		
		
		// Client = MODULE = NAMESPACE = PACKAGE
		// ? = ENTITAT DINS DEL MODULE : CLIENT
		IEntityService<Empresa> s = this._empresaService.createService();
		
		service.Add(value);
		
	}


	@Override
	public void addModalpanelById(K value) {
		// TODO Auto-generated method stub
		
	}
	
	


}
