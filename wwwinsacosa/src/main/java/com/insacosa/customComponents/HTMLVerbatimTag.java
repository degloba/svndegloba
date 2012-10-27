package com.insacosa.customComponents;

import javax.faces.webapp.UIComponentELTag;
import javax.faces.component.UIComponent;
import javax.el.ValueExpression;


public class HTMLVerbatimTag extends UIComponentELTag {
	
    //Temporary holder property hence if I put
    //&lt;tag:htmlVerbatim value="#{SomeBackingBean.htmlText}"/&gt;
    //information is stored in this object until the data is pushed into the component in setProperties method
    private ValueExpression value = null;
    public HTMLVerbatimTag() {}
    public ValueExpression getValue() { return this.value;}
    public void setValue(ValueExpression value) { this.value = value;}
 
    //This is where the action happens
    //Data is taken from the tag on the JSF Page and placed into the Component
    @Override
    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        HTMLVerbatimComponent verbatimComponent = (HTMLVerbatimComponent)component;
        if(value != null) {
            verbatimComponent.setValueExpression("value", value);
        }
    }
 
    //Method links this tag object to the component in config files
    public String getComponentType() { return "com.mdb.web.jsf.component.HTMLVerbatim"; }
 
    //Method links this tag object to the renderer in config files
    public String getRendererType() { return "com.mdb.web.jsf.component.HTMLVerbatim";}
 
}
