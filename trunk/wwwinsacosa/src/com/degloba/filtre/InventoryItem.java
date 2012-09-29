package com.degloba.filtre;

import java.io.Serializable;

public class InventoryItem extends InventoryFiltreItem implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 2052446469750935597L;
    //private String tipus;
    private String filtre;

    public String getFiltre() {
        return filtre;
    }

    public void setFiltre(String filtre) {
        this.filtre = filtre;
    }
}
