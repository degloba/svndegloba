package com.insacosa.presentation;

import java.util.List;

import com.insacosa.webui.CiutatItemDto;
import com.insacosa.webui.ProvinciaItemDto;


public interface CiutatsFinder {

	List<CiutatItemDto> findCiutats();

	List<CiutatItemDto> ciutatsProvincia(ProvinciaItemDto provincia);

    
}
