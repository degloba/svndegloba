package com.insacosa.presentation;

import java.util.List;

public interface TipusFinder {
    /**
     * Method for orders manager.
     */
    List<TipusDto> findTipus();

    
    TipusDto tipusInmoble(String keyInmoble);
}
