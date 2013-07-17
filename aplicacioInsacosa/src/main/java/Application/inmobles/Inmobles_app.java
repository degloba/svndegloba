package Application.inmobles;

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

public class Inmobles_app extends InsacosaClasseApp
	implements IInmobles 
	 {

	public Inmobles_app(IInsacosaClasseService ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}
	
	
	public Inmobles inmoblePerKey(String keyInmoble) {
		return null;
		
	}


	public Inmobles afegirInmoble(Inmobles idInmoble) {
		// TODO Auto-generated method stub
		return null;
	}


	public void modificarInmoble(Inmobles inmoble) {
		// TODO Auto-generated method stub
		
	}


	public void eliminarInmoble(Inmobles idInmoble) {
		// TODO Auto-generated method stub
		
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
	

}
