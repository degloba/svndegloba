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
import com.insacosa.vo.FotoForm;
import com.insacosa.webui.FotoItemDto;
import com.insacosa.webui.InmobleItemDto;
import com.insacosa.webui.ProvinciaItemDto;
import com.insacosa.webui.SolicitudItemDto;
import com.insacosa.webui.UsuariItemDto;

@ApplicationService
public class InmoblesApplicationService extends BaseApplicationService
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

	

	public void modificarInmoble(Inmobles inmoble) {

	}


	public void eliminarInmoble(Key key) {
	
	}



	public void solicitarInmobles(Solicituds solicitud) {
		// TODO Auto-generated method stub
		
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



	public int retId(String taula, String classe) {
		// TODO Auto-generated method stub
		return 0;
	}


	public void create() {
		// TODO Auto-generated method stub
		
	}



	public void modificarValorCaract(String string, String keyInmoble,
			String value) {
		// TODO Auto-generated method stub
		
	}


	public void eliminarValorCaract(Key keyCaracteristica, String keyInmoble) {
		// TODO Auto-generated method stub
		
	}




	public void eliminarSolicitud(SolicitudItemDto solicitud) {
		// TODO Auto-generated method stub
		
	}


	public void afegirInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		
	}


	public List<FotoForm> inmoblesVenedor(UsuariItemDto usuari) {
		// TODO Auto-generated method stub
		return null;
	}


	public void eliminarFoto(FotoItemDto foto) {
		// TODO Auto-generated method stub
		
	}


	public void eliminarValorCaract(Object next, String keyInmoble) {
		// TODO Auto-generated method stub
		
	}



	public InmobleItemDto esborrarInmoble(String keyInmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	
	

}
