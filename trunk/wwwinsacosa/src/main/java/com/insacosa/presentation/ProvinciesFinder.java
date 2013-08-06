package com.insacosa.presentation;

import java.util.List;

import com.insacosa.domain.Provincies;
import com.insacosa.webui.ProvinciaItemDto;



public interface ProvinciesFinder {

	List<Provincies> findProvincies();

	Provincies provinciaPerKey(String newValue);
    
}
