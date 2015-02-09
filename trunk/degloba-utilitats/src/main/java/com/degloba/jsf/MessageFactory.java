package com.degloba.jsf;

import java.util.ResourceBundle;
import java.util.Locale;
import javax.faces.context.FacesContext;

public class MessageFactory {
	ResourceBundle bundle;
	Locale locale;
	
	public MessageFactory() {
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		bundle = ResourceBundle.getBundle("LRAppResourceBundle", locale);
	}
	public String getMessage(String key) {
		return bundle.getString(key);
	}
}