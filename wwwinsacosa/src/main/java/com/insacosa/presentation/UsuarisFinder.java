package com.insacosa.presentation;

import java.util.List;


import com.insacosa.Inmobles.domain.Inmobles;
import com.insacosa.Inmobles.domain.Usuaris;

import com.insacosa.Inmobles.webui.UsuariItemDto;


public interface UsuarisFinder {

	List<UsuariItemDto> findUsuaris();

	UsuariItemDto cercarUsuari(String string);

	Usuaris usuariValid(Usuaris usuari);

	List<Usuaris> solicitantsInmoble(Inmobles inmoble);

}