package com.insacosa.vo;

import com.google.appengine.api.datastore.Key;
import com.insacosa.interfaces.Usuari_Impl;

import javax.servlet.http.*;
import javax.faces.context.*;

import com.insacosa.entitats.*;


public class LoginForm  {
	
	private Key key;
	private String text;
	private String password;

	public Key getkey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getText() {
		return text;
	}
	public void setText(String text){
		this.text = text;
	}


	public String validUser() throws Exception{
		
		//Interfaces interfaceS = serviceFinder.findBean("Interfaces");
		Usuari_Impl r = new Usuari_Impl();
		
		Usuaris usuari = new Usuaris();
		usuari.setKey(key);
		
		if (r.usuariValid(usuari))
		{
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
			usuari = r.cercarUsuari(key);
			
			if (usuari.getPassword().equals(this.password))
			{
				session.setAttribute("key", usuari);  // Guardem el registre en sessio
			
				// per
				UserForm userForm = (UserForm) context.getApplication().evaluateExpressionGet(context, "#{userForm}", UserForm.class);
				
				userForm.setKey(usuari.getKey());
				userForm.setAdreca(usuari.getAdreca());
				userForm.setEmail(usuari.getEmail());
				// .........
				
				userForm.setLoginat(true);
				
				return "success";
			}
			else
			{
				setText("User Name or Password is incorrect.");
				return "failure";
			}
		}
		else
		{
			setText("User Name or Password is incorrect.");
			return "failure";
		}
		
	}

	public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		if(session==null){
			return "invalid";
		}
		else{
			
			// per 
			UserForm userForm = (UserForm) context.getApplication().evaluateExpressionGet(context, "#{userForm}", UserForm.class);
			userForm.setLoginat(false);
			
			session.invalidate();
			return "logoutsuccess";
		}
		
	}	

}
		
