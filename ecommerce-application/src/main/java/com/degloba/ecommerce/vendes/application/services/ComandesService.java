package com.degloba.ecommerce.vendes.application.services;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.degloba.domain.annotations.ApplicationService;
import com.degloba.ecommerce.compres.domain.factories.CompresFactory;
import com.degloba.ecommerce.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.vendes.application.exceptions.OfertaCanviadaException;
import com.degloba.ecommerce.vendes.comandes.cqrs.commands.DetallsComandaCommand;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendesRepository;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.client.Client;
import com.degloba.ecommerce.vendes.domain.services.SuggerimentService;
import com.degloba.ecommerce.vendes.ofertes.descomptes.domain.policies.IDescomptePolicy;
import com.degloba.ecommerce.vendes.ofertes.domain.factories.DescomptePolicyFactory;
import com.degloba.ecommerce.vendes.ofertes.domain.persistence.rdbms.jpa.Oferta;
import com.degloba.ecommerce.vendes.pagaments.domain.persistence.rdbms.jpa.Pagament;
import com.degloba.ecommerce.vendes.productes.domain.persistence.rdbms.jpa.Producte;
import com.degloba.ecommerce.vendes.reserves.domain.factories.ReservesFactory;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;
import com.degloba.persistence.rdbms.api.jpa.exceptions.DomainOperationException;




/**
 * Casos d'ùs de les comandes<br>
 * Cal tenir en compte que el llenguatge de l’aplicació és diferent (més simple) que el llenguatge del domini,</br>
 * per exemple: no volem exposar conceptes de domini com ara la {@link Compra} i la {@link Reserva} a les capes superiors, 
 * que les amagem sota el terme {@link Order}.  
 * <br>
 * Tècnicament, el servei d’aplicacions és només un munt de procediments; per tant, els principis d’OO (ex: CqS, SOLID, GRASP) no s’apliquen aquí
 *
 * @author degloba
 */
@ApplicationService
public class ComandesService implements IComandesService {

/*	@Inject
	private SystemUser systemUser;*/
	
	@Inject
	private IVendesRepository vendesRepository;

	@Inject
	private ReservesFactory reservesFactory;

	@Inject
	private CompresFactory compresFactory;
	
/*	@Inject
	private IRepository productRepository;*/
	
	@Inject
	private DescomptePolicyFactory descomptePolicyFactory;
	
	@Inject
	private SuggerimentService suggerimentService;

	// @Secured requires BUYER role
	public AggregateId creaComanda() {
		Reserva reserva = reservesFactory.crea(loadClient());
		vendesRepository.save(reserva);
		return reserva.getAggregateId();
	}

	/**
	 * DOMAIN STORY<br>
	 * try to read this as a full sentence, this way: subject.predicate(completion)<br>
	 * <br>
	 * Carga la {@link Reserva} per la comandaId<br>
	 * Carga el {@link Producte} pel producteId<br>
	 * Comprova si el {@link Producte} no és accessible<br>
	 * si es així, llavors suggereix un equivalent d'aquest {@link Producte} basat en el client<br>
	 * Reservation add product by given quantitat
	 */
	@Override
	public void afegirProducte(AggregateId comandaId, AggregateId producteId, int quantitat) {
		Reserva reserva = vendesRepository.obtenirReservaById(Reserva.class,comandaId);
		
		/*Producte producte = productRepository.obtenirProducteById(Producte.class,producteId);
		
		if (! producte.isAvailabe()){
			Client client = loadClient();	
			producte = suggerimentService.suggerirProducteEquivalent(producte, client);
		}
			
		reserva.add(producte, quantitat);  */
		
		vendesRepository.save(reserva);
	}
	
	/**
	 * Can be invoked many times for the same order (with different params).<br>
	 * Offer VO is not stored in the Repo, it is stored on the Client Tier instead.
	 */
	public Oferta calculaOferta(AggregateId comandaId) {
		Reserva reserva = vendesRepository.obtenirReservaById(Reserva.class,comandaId);

		IDescomptePolicy iDescomptePolicy = descomptePolicyFactory.crea(loadClient());
		
		/*
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 */
		return reserva.calculateOffer(iDescomptePolicy);
	}

	/**
	 * DOMAIN STORY<br>
	 * try to read this as a full sentence, this way: subject.predicate(completion)<br>
	 * <br>
	 * Load reservation by comandaId<br>
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
	public void confirma(AggregateId comandaId, DetallsComandaCommand detallsComandaCommand, Oferta seenOffer)
			throws OfertaCanviadaException {
		Reserva reserva = vendesRepository.obtenirReservaById(Reserva.class,comandaId);
		if (reserva.isClosed())
			throw new DomainOperationException(reserva.getAggregateId(), "reservation is already closed");
		
		/*
		 * Sample pattern: Aggregate generates Value Object using function<br>
		 * Higher order function is closured by policy
		 */
		Oferta newOffer = reserva.calculateOffer(descomptePolicyFactory.crea(loadClient()));
		
		/*
		 * Sample pattern: Client Tier sends back old VOs, Server generates new VOs based on Aggregate state<br>
		 * Notice that this VO is not stored in Repo, it's stored on the Client Tier. 
		 */
		if (! newOffer.sameAs(seenOffer, 5))//TODO load delta from conf.
			throw new OfertaCanviadaException(reserva.getAggregateId(), seenOffer, newOffer);
		
		Client client = loadClient();//create per logged client, not reservation owner					
		Compra compra = compresFactory.create(reserva.getAggregateId(), client, seenOffer);
				
		if (! client.canAfford(compra.getTotalCost()))
			throw new DomainOperationException(client.getAggregateId(), "client has insufficent money");
		
		vendesRepository.save(compra);//Aggregate must be managed by persistence context before firing events (synchronous listeners may need to load it) 
		
		/*
		 * Sample model where one aggregate creates another. Client does not manage payment lifecycle, therefore application must manage it. 
		 */
		Pagament pagament = client.charge(compra.getTotalCost());
		vendesRepository.save(pagament);
		
		compra.confirm();	
		reserva.close();				
		
		vendesRepository.save(reserva);
		vendesRepository.save(client);
		
	}
	
	private Client loadClient() {
		return null;
		////////////////return entityRepository.load(systemUser.getDomainUserId());
	}


	}
