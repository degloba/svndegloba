package com.insacosa.application.services;

import javax.inject.Inject;

import com.google.appengine.api.datastore.Key;

// Repsoitoris
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.ProductRepository;

import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.OrderFactory;



import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;

import com.insacosa.domain.*;

public class UsuarisAplicationService<K,T extends Usuaris> extends  GenericApplicationServiceForBaseEntity <K,T>

	 {

	    @Inject
	    private SystemUser systemUser;

	    @Inject
	    private ApplicationEventPublisher eventPublisher;

	    
	public void afegirUsuari(T usuari) {
		
		
	}

	public String modificarUsuari(Usuaris usuari) {
		return "";
		
	}

	public void eliminarUsuari(Key key) {
	
	}

	public Usuaris cercarUsuari(Key key) {
		return null;
		
	}

	public Usuaris editPerfil(String nomUsuari) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuaris usuariValid(Usuaris usuari) {
		return usuari;
		
		 //return ds.CreateService();		
	}

	public boolean emailValid(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public String cambiaPassword(String usuari, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	public ApplicationEventPublisher getEventPublisher() {
		return eventPublisher;
	}

	public void setEventPublisher(ApplicationEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}
	
	
	

}
