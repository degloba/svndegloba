package com.insacosa.presentation;

import java.util.List;

import com.insacosa.domain.Caracteristiques;
import com.insacosa.domain.Tipus;


public interface TipusFinder {
    /**
     * Method for orders manager.
     */
    List<TipusDto> findTipus();

    
    TipusDto tipusInmoble(String keyInmoble);


	List<Caracteristiques> caractTipus(Tipus tipus, int i, boolean b);


	List<entitats.Caracteristiques> caractTipus(entitats.Tipus tipus, int i,
			boolean b);
}
