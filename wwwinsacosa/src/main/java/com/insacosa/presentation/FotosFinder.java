package com.insacosa.presentation;

import java.util.List;

import com.insacosa.domain.Fotos;
import com.insacosa.domain.Inmobles;
import com.insacosa.webui.CiutatItemDto;
import com.insacosa.webui.ProvinciaItemDto;


public interface FotosFinder {

	List<Fotos> fotosInmoble(Inmobles inmoble);

	    
}
