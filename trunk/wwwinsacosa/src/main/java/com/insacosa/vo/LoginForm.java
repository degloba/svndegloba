package com.insacosa.vo;

import com.google.appengine.api.datastore.Key;
import com.insacosa.interfaces.Usuari_Impl;

import javax.servlet.http.*;
import javax.faces.context.*;

import com.insacosa.entitats.*;


public class LoginForm  {
	
	private Key key;
	private String text;
	private String nomUsuari;
	private String password;

	public Key getkey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getNomUsuari() {
		return nomUsuari;
	}
	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
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
				
		Usuari_Impl r = new Usuari_Impl();
		
		Usuaris usuari = new Usuaris();
		usuari.setNomusuari(nomUsuari);
		
		usuari = r.usuariValid(usuari);
		
		if (usuari != null)  // existeix l'usuari ?
		{
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			
			
			//usuari = r.cercarUsuari(nomUsuari);
			
			if (usuari.getPassword().equals(this.password))
			{
				session.setAttribute("key", usuari);  // Guardem el registre en sessio
				
				// per
				UserForm userForm = (UserForm) context.getApplication().evaluateExpressionGet(context, "#{userForm}", UserForm.class);
				
				/////userForm.set(usuari.getKey());
				userForm.setAdreca(usuari.getAdreca());
				userForm.setEmail(usuari.getEmail());
				userForm.setCognoms(usuari.getCognoms());
				userForm.setLocalitat(usuari.getLocalitat());
				userForm.setProvincia(usuari.getProvincia());
				userForm.setProvincia(usuari.getProvincia());
				userForm.setTelefon(usuari.getTelefon());
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
		
