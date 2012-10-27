package com.insacosa.interfaces;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.insacosa.dataModels_JPA.InmobleCaract;

import com.insacosa.entitats.*;


public interface Inmoble_If {
	
	
	// ****************************
	// Operacions inmobles
	// ****************************
	Inmobles inmoblePerKey(Key keyInmoble);
	Inmobles afegirInmoble(Inmobles idInmoble);
	void modificarInmoble(Inmobles inmoble);
	void eliminarInmoble(Inmobles idInmoble);
	
	Tipus tipusInmoble(Key keyInmoble);
	
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
	Inmobles detallInmoble(Key id);
	
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

	
	
	// *************************************
	// Operacions caracteristiques d'inmoble
	// *************************************
	// caracteristiques d'un tipus d'inmoble amb i sense una condicio
	List<Caracteristiques> caractTipus(Tipus tipus);
	List<Caracteristiques> caractTipus(Tipus tipus, Integer condicio, Boolean inclouCaractComu);
	Caracteristiques caractPerKey(Key idCaract);
	List<Caracteristiques> allCaract();
	
	
	// caracteristica a partir d'una caracteristica d'inmoble
	Caracteristiques caracteristicaCaractInmoble(Caractinmobles caractinmoble);
	void afegirCaractInmoble(Caractinmobles caractinmoble);
	
	

	
	//Retorna el tipus COLUMNA (String,Integer,..) d'una caracteristica en concret
	// * Exemple : metres --> integer, adre�a --> string,...
	String tipusColumnaCaract(Key idCaract);
	String tipusColumnaCaract(String idCaract);
	
	/*
	 * Retorna el tipus de CONTROL UI (ITXT,SELT,IRAD,FILE,,..) d'una caracteristica en concret
	 * Exemple : metres --> ITXT, provincia --> SELT , ...
	 */
	String tipusControlUICaract(Key idCaract);
	String tipusControlUICaract(String nomCaract);
		
	
	// ****************************
	// Provincies
	// ****************************
	Provincies provinciaPerKey(Key idProvincia);
	
	
	
	// ****************************
	// Ciutats
	// ****************************
	Ciutats ciutatPerKey(Key idCiutat);
	List<Ciutats> ciutatsProvincia(Provincies provincia);
	
	
	// ****************************
	// Objectes - Operacions comunes
	// ****************************
	// retorna la llista d'objectes d'una classe aplicant una condicio i ordre
	List<Objecte> llistaObjectes(Class classe, String ordre, String condicio);
	
	// retorna un objecte a partir del seu Id (objecte) 
	Objecte read(Objecte objecte);
	Objecte objectePerKey(Key id);
	
	// retorna el max(ID) d'una classe
	int retId(String taula, String classe);
	
	// retorna la descripcio corresponent a un ID 
	Objecte retDescripcio(Class entityName, Key id);
	
	// esborra un objecte
	void delete(Objecte objecte);
	
	// crea un objecte
	void create();
	
	// modifica un objecte
	void update(Objecte objecte);
	
	
	
	// ****************************
	// Valors caracteristiques
	// ****************************
	void afegirValorCaract(ValuesCaracteristiques valorCaracteristica);
	void eliminarValorCaract(Key idCaracteristica, Key idInmoble);
	void modificarValorCaract(Key idCaracteristica, Key idInmoble, String value);
	InmobleCaract valorsCaracteristiquesInmoble(Key idInmoble);
	
	

}
