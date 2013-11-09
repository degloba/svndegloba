package com.degloba.utils;


import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.MethodExpressionActionListener;

//import org.apache.el.ExpressionFactoryImpl;

public class Utils {
	
	
	public static MethodExpression createAction(String actionExpression, Class<?> returnType) { 
	    FacesContext context = FacesContext.getCurrentInstance(); 
	    return context.getApplication().getExpressionFactory() 
	        .createMethodExpression(context.getELContext(), actionExpression, returnType, new Class[0]); 
	} 
	 
	//Create ActionListener expression in JSF 1.2 or newer:
	public static MethodExpressionActionListener createActionListener(String actionListenerExpression) { 
	    FacesContext context = FacesContext.getCurrentInstance(); 
	    return new MethodExpressionActionListener(context.getApplication().getExpressionFactory() 
	        .createMethodExpression(context.getELContext(), actionListenerExpression, null, new Class[] {ActionEvent.class})); 
	} 

	
	
	/*
	 * Retorna el bean JSF a partir del seu nom 
	 */
	public static Object accessBeanFromFacesContext(final String theBeanName, final FacesContext context) { 
		
		final Object returnObject = context.getELContext().getELResolver().getValue(context.getELContext(), null, theBeanName);  
		
		if (returnObject == null) { 
			System.out.println("Bean amb nom " + theBeanName + " no s'ha trobat. Revisa el fitxer faces-config.xml file per si el nom es correcte.");  
		}
		
		return returnObject;
	}
	
	
	/**
     * Method for taking a reference to a JSF binding expression and returning
     * the matching object (or creating it).
     * @param expression EL expression
     * @return Managed object
     */
    public static Object resolveExpression(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = 
            elFactory.createValueExpression(elContext, expression, 
                                            Object.class);
        //return valueExp.getValue(elContext);
        return valueExp;
   
    }
    


    public final static void processProperty(final UIComponent component, final ValueExpression property,
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
			
	/*public void setStyleClass(ValueExpression styleClass) {
			this.styleClass = styleClass;
	}
*/

}
