package com.insacosa.application.services;

import java.util.List;

import javax.inject.Inject;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.QueryResultList;
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.OrderFactory;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.ProductRepository;

import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import ddd.application.annotation.ApplicationService;


import com.insacosa.domain.*;

@ApplicationService
public class Inmobles_app 
	 {
	
	 	@Inject
	    private OrderRepository orderRepository;

	    @Inject
	    private OrderFactory orderFactory;

	    @Inject
	    private ProductRepository productRepository;

	    @Inject
	    private InvoiceRepository invoiceRepository;

	    @Inject
	    private InvoicingService invoicingService;

	    @Inject
	    private SystemUser systemUser;

	    @Inject
	    private ApplicationEventPublisher eventPublisher;

	
	
	public Inmobles inmoblePerKey(Key key) {
		return null;
		
	}


	public void afegirInmoble(Inmobles inmoble) {
		
	}


	public void modificarInmoble(Inmobles inmoble) {

	}


	public void eliminarInmoble(Key key) {
	
	}


	public List<Inmobles> buscarInmobles(Inmobles condicioInmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Inmobles> inmoblesSolicitats(Usuaris usuariVenedor) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Inmobles> inmoblesSolicitatsPerUsuari(Usuaris usuariComprador) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Inmobles> inmoblesVenedor(Usuaris venedor) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Inmobles> inmoblesVenedorRang(Usuaris usuari, int firstResult,
			int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}


	public Inmobles detallInmoble(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	public void solicitarInmobles(Solicituds solicitud) {
		// TODO Auto-generated method stub
		
	}


	public List<Inmobles> inmoblesTipus() {
		// TODO Auto-generated method stub
		return null;
	}


	public void afegirFoto(Fotos foto) {
		// TODO Auto-generated method stub
		
	}


	public void modificarFoto(Fotos foto) {
		// TODO Auto-generated method stub
		
	}


	public void eliminarFoto(Fotos foto) {
		// TODO Auto-generated method stub
		
	}


	public List<Fotos> fotosInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	public void eliminarSolicitud(Solicituds solicitud) {
		// TODO Auto-generated method stub
		
	}


	public List<Usuaris> solicitantsInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	public void afegirCaractInmoble(Caractinmobles caractinmoble) {
		// TODO Auto-generated method stub
		
	}


	public String tipusColumnaCaract(Key idCaract) {
		// TODO Auto-generated method stub
		return null;
	}


	public String tipusColumnaCaract(String idCaract) {
		// TODO Auto-generated method stub
		return null;
	}


	public String tipusControlUICaract(Key idCaract) {
		// TODO Auto-generated method stub
		return null;
	}


	public String tipusControlUICaract(String nomCaract) {
		// TODO Auto-generated method stub
		return null;
	}


	public QueryResultList<Entity> llistaObjectes(Class classe, String ordre,
			String condicio) {
		// TODO Auto-generated method stub
		return null;
	}


	public int retId(String taula, String classe) {
		// TODO Auto-generated method stub
		return 0;
	}


	public void create() {
		// TODO Auto-generated method stub
		
	}


	public InmobleCaract valorsCaracteristiquesInmoble(String keyInmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	public void modificarValorCaract(Key idCaracteristica, String keyInmoble,
			String value) {
		// TODO Auto-generated method stub
		
	}


	public void eliminarValorCaract(Key keyCaracteristica, String keyInmoble) {
		// TODO Auto-generated method stub
		
	}


	public Inmobles inmoblePerKey(String keyInmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
