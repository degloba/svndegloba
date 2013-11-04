package com.insacosa.presentation;

import java.util.List;

import com.insacosa.domain.Ciutats;
import com.insacosa.domain.Provincies;


public interface CiutatsFinder {

	List<Ciutats> findCiutats();

	List<Ciutats> ciutatsProvincia(Provincies provincia);

    
}
