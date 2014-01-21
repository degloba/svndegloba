package com.insacosa.presentation;

import java.util.List;


import com.google.appengine.api.datastore.Key;
import com.insacosa.Inmobles.domain.InmobleCaract;
import com.insacosa.Inmobles.domain.Inmobles;
import com.insacosa.Inmobles.domain.Usuaris;
import com.insacosa.Inmobles.webui.InmobleItemDto;
import com.insacosa.Inmobles.webui.UsuariItemDto;


public interface InmoblesFinder {

	List<InmobleItemDto> findInmobles();

	List<Inmobles> inmoblesSolicitatsPerUsuari(Long usuariId);

	Inmobles detallInmoble(String keyInmoble);

	List<InmobleItemDto> inmoblesVenedorRang(UsuariItemDto usuari, int i, int j);

	InmobleCaract valorsCaracteristiquesInmoble(Object inmobleKey);

	List<Inmobles> inmoblesVenedor(UsuariItemDto usuari);

	List<Inmobles> buscarInmobles(Inmobles condicioInmoble);

	Inmobles inmoblePerKey(String keyInmoble);

	List<Inmobles> inmoblesSolicitats(Usuaris usuariVenedor);

	List<Inmobles> inmoblesVenedor(Usuaris usuari);

	List<InmobleCaract> getInmoblesVenedorCaract();

	List<Inmobles> inmoblesTipus();

	String tipusColumnaCaract(String propertyName);

	String tipusColumnaCaract(Key idCaract);
    
}
