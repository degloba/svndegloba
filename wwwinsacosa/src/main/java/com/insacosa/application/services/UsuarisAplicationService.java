package com.insacosa.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.insacosa.domain.InvoiceRepository;
import com.insacosa.domain.InvoicingService;
import com.insacosa.domain.OrderFactory;
import com.insacosa.domain.OrderRepository;
import com.insacosa.domain.ProductRepository;

import application.InsacosaClasseApp;

import ddd.application.ApplicationEventPublisher;
import ddd.application.SystemUser;
import domini.IInsacosaClasseService;

import com.insacosa.domain.*;

public class UsuarisAplicationService

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

	public void afegirUsuari(Usuaris usuari) {
		// TODO Auto-generated method stub
		

		
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

	public String cambiaPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
