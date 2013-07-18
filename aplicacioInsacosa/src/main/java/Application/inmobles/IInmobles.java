package Application.inmobles;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.QueryResultList;

//import com.insacosa.interfaces.Objecte;

import entitats.Caractinmobles;
import entitats.Fotos;
import entitats.InmobleCaract;
import entitats.Inmobles;
import entitats.Solicituds;
import entitats.Usuaris;
import Application.IInsacosaClasseApp;

public interface IInmobles extends IInsacosaClasseApp {
	
	Inmobles inmoblePerKey(String key);
	void afegirInmoble(Inmobles inmoble);
	void modificarInmoble(Inmobles inmoble);
	void eliminarInmoble(Key key);
	
	// llista tots els inmobles cercats segons filtre
		List<Inmobles> buscarInmobles(Inmobles condicioInmoble);
		
		// llista tots els inmobles d'un venedor solicitats
		List<Inmobles> inmoblesSolicitats(Usuaris usuariVenedor);
		
		// llista tots els inmobles solicitats per un determinat comprador
		List<Inmobles> inmoblesSolicitatsPerUsuari (Usuaris usuariComprador);
		
		// llista inmobles d'un venedor
		List<Inmobles> inmoblesVenedor(Usuaris venedor);
		List<Inmobles> inmoblesVenedorRang(Usuaris usuari, int firstResult, int maxResults);
		
		// retorna un inmoble amb tota la informacio (detall)
		Inmobles detallInmoble(String id);
		
		// un comprador (sessio) que "marca" un inmoble com solicitat
		void solicitarInmobles(Solicituds solicitud);
		
		// llista els inmobles d'un tipus
		List<Inmobles> inmoblesTipus();
		
		
		// ****************************
		// Operacions fotos
		// ****************************
		void afegirFoto(Fotos foto);
		void modificarFoto(Fotos foto);
		void eliminarFoto(Fotos foto);
		
		List<Fotos> fotosInmoble(Inmobles inmoble);
		
		
		// ****************************
		// Operacions solicituds
		// ****************************
		void eliminarSolicitud(Solicituds solicitud);
		
		// llista de solicitants (usuaris) per un inmoble determinat
		List<Usuaris> solicitantsInmoble(Inmobles inmoble);

		
		void afegirCaractInmoble(Caractinmobles caractinmoble);
		
		
		//Retorna el tipus COLUMNA (String,Integer,..) d'una caracteristica en concret
		// * Exemple : metres --> integer, adreça --> string,...
		String tipusColumnaCaract(Key idCaract);
		String tipusColumnaCaract(String idCaract);
		
		/*
		 * Retorna el tipus de CONTROL UI (ITXT,SELT,IRAD,FILE,,..) d'una caracteristica en concret
		 * Exemple : metres --> ITXT, provincia --> SELT , ...
		 */
		String tipusControlUICaract(Key idCaract);
		String tipusControlUICaract(String nomCaract);
			
		
		
		// ****************************
		// Objectes - Operacions comunes
		// ****************************
		// retorna la llista d'objectes d'una classe aplicant una condicio i ordre
		QueryResultList<Entity> llistaObjectes(Class classe, String ordre, String condicio);
		
		// retorna un objecte a partir del seu Id (objecte) 
		//Objecte read(Objecte objecte);
		//Objecte objectePerKey(Key id);
		
		// retorna el max(ID) d'una classe
		int retId(String taula, String classe);
		
		// retorna la descripcio corresponent a un ID 
		//Objecte retDescripcio(Class entityName, String id);
		
		// esborra un objecte
		//void delete(Objecte objecte);
		
		// crea un objecte
		void create();
		
		// modifica un objecte
		//void update(Objecte objecte);
		
		InmobleCaract valorsCaracteristiquesInmoble(String keyInmoble);
		void modificarValorCaract(Key idCaracteristica, String keyInmoble, String value);
		void eliminarValorCaract(Key keyCaracteristica, String keyInmoble);
		

}
