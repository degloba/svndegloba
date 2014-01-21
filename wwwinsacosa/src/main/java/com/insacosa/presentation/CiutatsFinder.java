package com.insacosa.presentation;

import java.util.List;

import com.insacosa.Inmobles.domain.Ciutats;
import com.insacosa.Inmobles.domain.Provincies;


public interface CiutatsFinder {

	List<Ciutats> findCiutats();

	List<Ciutats> ciutatsProvincia(Provincies provincia);

    
}
