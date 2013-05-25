package com.insacosa.interfaces;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.QueryResultList;
import com.insacosa.dataModels_JPA.InmobleCaract;

import com.insacosa.entitats.*;


public interface Caracteristiques_If {
	
	
	// *************************************
	// Operacions caracteristiques d'inmoble
	// *************************************
	// caracteristiques d'un tipus d'inmoble amb i sense una condicio
	List<Caracteristiques> caractTipus(Tipus tipus);
	List<Caracteristiques> caractTipus(Tipus tipus, Integer condicio, Boolean inclouCaractComu);
	Caracteristiques caractPerKey(String idCaract);
	List<Caracteristiques> allCaract();
	Caracteristiques caracteristicaCaractInmoble(Caractinmobles caractinmoble);

}
