package com.insacosa.filtre;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;

public class InventoryItem extends InventoryFiltreItem implements Serializable{

    private static final long serialVersionUID = 2052446469750935597L;

    private Key filtre;

    public Key getFiltre() {
        return filtre;
    }

    public void setFiltre(Key filtre) {
        this.filtre = filtre;
    }
}
