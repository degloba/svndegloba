package com.insacosa.filtre;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InventoryFiltreList implements Serializable {
    private static final long serialVersionUID = -6547391197128734913L;

    //private String tipus;  // ex: habitacions, banys,.. (representa el Filtre)
    private String filtre;  // ex: habitacions, banys,.. (representa el Filtre)
    private List<InventoryFiltreItem> filtreItems;

    public InventoryFiltreList() {
    	filtreItems = new ArrayList<InventoryFiltreItem>();
    }

    public long getCount() {
        if (filtreItems != null) {
            return filtreItems.size();
        } else {
            return 0;
        }
    }

    public String getFiltre() {
        return filtre;
    }

    public void setFiltre(String filtre) {
        this.filtre = filtre;
    }

    public List<InventoryFiltreItem> getFiltreItems() {
        return filtreItems;
    }

    public void setFiltreItems(List<InventoryFiltreItem> filtreItems) {
        this.filtreItems = filtreItems;
    }
}
