package com.insacosa.presentation;

import java.util.List;

import com.insacosa.domain.Provincies;


public interface ProvinciesFinder {

	List<Provincies> findProvincies();

	Provincies provinciaPerKey(String newValue);
    
}
