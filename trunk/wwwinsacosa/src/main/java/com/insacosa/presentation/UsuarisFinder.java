package com.insacosa.presentation;

import java.util.List;


import com.insacosa.domain.Inmobles;
import com.insacosa.domain.Usuaris;
import com.insacosa.webui.InmobleItemDto;
import com.insacosa.webui.UsuariItemDto;



public interface UsuarisFinder {

	List<UsuariItemDto> findUsuaris();

	UsuariItemDto cercarUsuari(String string);

	Usuaris usuariValid(Usuaris usuari);

	List<Usuaris> solicitantsInmoble(Inmobles inmoble);

}
