package com.insacosa.presentation;

import java.util.List;

import com.insacosa.Inmobles.domain.Fotos;
import com.insacosa.Inmobles.domain.Inmobles;


public interface FotosFinder {

	List<Fotos> fotosInmoble(Inmobles inmoble);

	    
}
