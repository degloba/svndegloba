package com.insacosa.presentation;

import java.util.List;

import com.insacosa.domain.Ciutats;
import com.insacosa.webui.CiutatItemDto;
import com.insacosa.webui.ProvinciaItemDto;


public interface CiutatsFinder {

	List<Ciutats> findCiutats();

	List<CiutatItemDto> ciutatsProvincia(ProvinciaItemDto provincia);

    
}
