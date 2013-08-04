package com.insacosa.presentation;

import java.util.List;

import entitats.Caracteristiques;
import entitats.Tipus;

public interface TipusFinder {
    /**
     * Method for orders manager.
     */
    List<TipusDto> findTipus();

    
    TipusDto tipusInmoble(String keyInmoble);


	List<Caracteristiques> caractTipus(Tipus tipus, int i, boolean b);
}
