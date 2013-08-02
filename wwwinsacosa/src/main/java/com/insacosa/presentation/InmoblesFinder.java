package com.insacosa.presentation;

import java.util.List;


import com.insacosa.domain.InmobleCaract;
import com.insacosa.vo.FotoForm;
import com.insacosa.webui.InmobleItemDto;
import com.insacosa.webui.UsuariItemDto;



public interface InmoblesFinder {

	List<InmobleItemDto> findInmobles();

	List<InmobleItemDto> inmoblesSolicitatsPerUsuari(Long usuariId);

	InmobleItemDto detallInmoble(String keyInmoble);

	List<InmobleItemDto> inmoblesVenedorRang(UsuariItemDto usuari, int i, int j);

	InmobleCaract valorsCaracteristiquesInmoble(Object inmobleKey);

	List<InmobleItemDto> inmoblesVenedor(UsuariItemDto usuari);
    
}
