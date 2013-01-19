package com.degloba.customComponents;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

public class Shuffler extends UIComponentBase {

    public static final String FAMILY = "nl.amis.Shuffler"; // org.richfaces.component.
    public static final String STYLECLASS_ATTRIBUTE_KEY = "styleClass"; // atribut

    public String getFamily() {
        return FAMILY;
    }

    @Override
    public Object saveState(FacesContext facesContext) {
        Object values[] = new Object[2];
        values[0] = super.saveState(facesContext);
        values[1] = this.getAttributes().get(STYLECLASS_ATTRIBUTE_KEY);
        return values;

    }

    @Override
    public void restoreState(FacesContext facesContext, Object state) {
        Object values[] = (Object[])state;
        super.restoreState(facesContext, values[0]);
        this.getAttributes().put(STYLECLASS_ATTRIBUTE_KEY, values[1]);
    }
}
