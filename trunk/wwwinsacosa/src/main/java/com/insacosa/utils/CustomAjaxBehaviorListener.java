package com.insacosa.utils;

import javax.faces.component.html.HtmlInputText;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;


public class CustomAjaxBehaviorListener implements AjaxBehaviorListener { 
	
	

	
	public CustomAjaxBehaviorListener() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void processAjaxBehavior(AjaxBehaviorEvent event)
			throws AbortProcessingException {
		// TODO Auto-generated method stub
		
		HtmlInputText inputText = (HtmlInputText) event.getComponent(); // your input text component 
	       
		System.out.println("El component es : " + inputText.getClientId());
		
		
	} 
 
} 
