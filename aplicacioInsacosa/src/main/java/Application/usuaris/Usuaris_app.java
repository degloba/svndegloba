package Application.usuaris;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.QueryResultList;

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
		
	}

	public String modificarUsuari(Usuaris usuari) {
		// TODO Auto-generated method stub
		return null;
	}

	public void eliminarUsuari(Key usuari) {
		// TODO Auto-generated method stub
		
	}

	public Usuaris cercarUsuari(String usuari) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuaris editPerfil(String nomUsuari) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuaris usuariValid(Usuaris usuari) {
		// TODO Auto-generated method stub
		return null;
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
