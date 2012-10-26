package com.insacosa.customComponents;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.el.ValueExpression;
 
public class HTMLVerbatimComponent extends UIComponentBase {
 
    public HTMLVerbatimComponent() { }
 
    //Properties you want exposed as tag values must be exposed using getters/setters
    private String value;
    public String getValue() {
        if(value != null)
            return value;
        //If the value has not already been set interpret the Expression language and get resulting object
        Object value = this.getValueExpressionValue("value");
        return value != null ? (String)value : null;
    }
 
    public void setValue(String value) { this.value = value;}
 
    //Honestly I am not sure what this method does besides link the component to the config files
    public String getFamily() {
        return "com.mdb.web.jsf.component.HTMLVerbatim";
    }
 
    //Method allows component state to be stored
    @Override
    public Object saveState(FacesContext context) {
        return new Object[] { super.saveState(context), value};
    }
 
    //Method allows component state to be restored
    @Override
    public void restoreState(FacesContext context, Object object) {
        Object state[] = (Object[]) object;
        super.restoreState(context, state[0]);
        value = (String)state[1];
    }
 
    //Helper method to simplify getters/setters
    //This helps the JSF Component use Expression Language
    private Object getValueExpressionValue(String name) {
        ValueExpression ve = super.getValueExpression(name);
        return ve.getValue(FacesContext.getCurrentInstance().getELContext());
    }
}
