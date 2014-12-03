package com.degloba.boundedContext.ui.webui.customComponents.jsf;

import javax.el.ValueExpression;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

public class ShufflerTag extends UIComponentELTag {

    public static final String COMPONENT_TYPE = "nl.amis.jsf.UIShuffler"; // org.richfaces.component.
    public static final String RENDERER_TYPE = "nl.amis.jsf.ShufflerRenderer"; //

    private ValueExpression styleClass;

    public String getComponentType() {
        return COMPONENT_TYPE;
    }

    public String getRendererType() {
        return RENDERER_TYPE;
    }

    protected void setProperties(UIComponent component) {
        super.setProperties(component);
        processProperty(component, styleClass, Shuffler.STYLECLASS_ATTRIBUTE_KEY);
    }

    public void release() {
        super.release();
        styleClass= null;
    }

    protected final void processProperty(final UIComponent component, final ValueExpression property,
                                 final String propertyName) {
        if (property != null) {
            if(property.isLiteralText()) {
                component.getAttributes().put(propertyName, property.getExpressionString());
            }
            else {
                component.setValueExpression(propertyName, property);
            }
        }
    }

    public void setStyleClass(ValueExpression styleClass) {
        this.styleClass = styleClass;
    }
}

