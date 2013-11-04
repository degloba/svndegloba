package com.insacosa.presentation;

import java.util.List;

import com.insacosa.domain.Fotos;
import com.insacosa.domain.Inmobles;


public interface FotosFinder {

	List<Fotos> fotosInmoble(Inmobles inmoble);

	    
}
