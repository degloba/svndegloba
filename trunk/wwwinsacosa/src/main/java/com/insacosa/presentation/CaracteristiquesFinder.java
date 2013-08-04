package com.insacosa.presentation;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.insacosa.domain.Caracteristiques;
import com.insacosa.domain.Caractinmobles;
import com.insacosa.domain.Tipus;
import com.insacosa.webui.CiutatItemDto;
import com.insacosa.webui.ProvinciaItemDto;


public interface CaracteristiquesFinder {

	List<Caracteristiques> caractTipus(Tipus tipus, Integer condicio,
			Boolean inclouCaractComu);

	String tipusColumnaCaract(Key keyCaract);

	String tipusColumnaCaract(String nomCaract);

	String tipusControlUICaract(Key keyCaract);

	String tipusControlUICaract(String nomCaract);

	Caracteristiques caractPerKey(String keyCaract);

	List<Caracteristiques> allCaract();

	Caracteristiques caracteristicaCaractInmoble(Caractinmobles caractinmoble);

	List<Caracteristiques> caractTipus(Tipus tipus);


    
}
