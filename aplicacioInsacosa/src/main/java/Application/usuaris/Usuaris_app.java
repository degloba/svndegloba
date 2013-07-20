package Application.usuaris;

import java.util.ArrayList;
import java.util.List;

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

import Application.InsacosaClasseApp;

import domini.IInsacosaClasseService;
import entitats.Caractinmobles;
import entitats.Fotos;
import entitats.Inmobles;
import entitats.Solicituds;
import entitats.Usuaris;

public class Usuaris_app extends InsacosaClasseApp
	implements IUsuaris
	 {

	public Usuaris_app(IInsacosaClasseService ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	public void afegirUsuari(Usuaris usuari) {
		// TODO Auto-generated method stub
		
		createClasseApp(usuari);
		
	}

	public String modificarUsuari(Usuaris usuari) {
		saveClasseApp(usuari);
		
		return "";
		
	}

	public void eliminarUsuari(Key key) {
		deleteClasseAppByKey(key);		
	}

	public Usuaris cercarUsuari(Key key) {
		return getClasseAppByKey(key);		
	}

	public Usuaris editPerfil(String nomUsuari) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuaris usuariValid(Usuaris usuari) {
		
		 return ds.CreateService();		
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
