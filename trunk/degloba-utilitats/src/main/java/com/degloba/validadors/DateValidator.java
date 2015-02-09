package com.degloba.validadors;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

// http://byteco.de/2009/06/16/jsf-validations/
public class DateValidator implements Validator {


	public void validate(FacesContext facesContext, UIComponent component, Object object)
		throws ValidatorException {
	
	org.apache.commons.validator.DateValidator instance = org.apache.commons.validator.DateValidator.getInstance();
	
	if(!instance.isValid(object.toString(), "dd/MM/yyyy", true)){
	
		FacesMessage facesMessage = new FacesMessage("Invalid date");
	
		throw new ValidatorException(facesMessage);
	
	}

}

}
