package com.degloba.convertidors;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class DateConverter implements Converter {


	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String val)
		throws ConverterException {
	
	String pattern = "dd/MM/yyyy";
	
	boolean required = ((UIInput)uiComponent).isRequired();
	
	if((val==null || val.trim().isEmpty()) && !required )
	
		return null;
	
	org.apache.commons.validator.DateValidator instance = org.apache.commons.validator.DateValidator.getInstance();
	
	if(!instance.isValid(val.toString(), pattern, true)){
	
		FacesMessage facesMessage = new FacesMessage("Using Converter - Invalid date");
	
		throw new ConverterException(facesMessage);
	
	}
	
	try {
	
		return new SimpleDateFormat(pattern).parse(val);
	
	} catch (ParseException e) {
	
		FacesMessage facesMessage = new FacesMessage("Using Converter - Invalid date");
	
		throw new ConverterException(facesMessage);
	
		}
	
	}
	
	
	
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object val)
		throws ConverterException {
	
	return val.toString();
	
	}


}