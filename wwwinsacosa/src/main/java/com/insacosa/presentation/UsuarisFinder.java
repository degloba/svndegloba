package com.insacosa.presentation;

import java.util.List;


import com.insacosa.domain.Usuaris;
import com.insacosa.webui.InmobleItemDto;
import com.insacosa.webui.UsuariItemDto;



public interface UsuarisFinder {

	List<UsuariItemDto> findUsuaris();

	List<UsuariItemDto> solicitantsInmoble(InmobleItemDto inmoble);

	UsuariItemDto cercarUsuari(String string);

	Usuaris usuariValid(Usuaris usuari);

}
