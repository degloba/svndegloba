package com.insacosa.validadors;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


// http://java.dzone.com/articles/jsf-validation-tutorial-error
public class EmailValidator implements Validator{

	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
	{

		String email = (String) value;
	
		if(!email.contains("@")) {
		
			FacesMessage message = new FacesMessage();
			
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			
			message.setSummary("Email is not valid.");		
			message.setDetail("Email is not valid.");
			context.addMessage(component.getClientId(context), message);
		
			throw new ValidatorException(message);
		
		}
	
	}

}