package com.degloba;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

public class listShuttleConverter implements javax.faces.convert.Converter{

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		 String [] temp = null;
		 temp =value.split(":");
		
		return new ToolBarItem(temp[0].toString(), temp[1].toString() , temp[2].toString());
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		ToolBarItem optionItem = (ToolBarItem) value;
		return optionItem.getNom() + ":" + optionItem.getIconURI()+ ":" + optionItem.getFramework();
	}

}